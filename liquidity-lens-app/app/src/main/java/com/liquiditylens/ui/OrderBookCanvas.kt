package com.liquiditylens.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.animation.core.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import com.liquiditylens.data.MarketSnapshot
import com.liquiditylens.data.MarketDataWebSocket
import com.liquiditylens.data.MarketStats
import com.liquiditylens.data.calculateMarketStats
import com.liquiditylens.utils.ComposableCapture
import com.liquiditylens.utils.SnapshotExporter

/**
 * Order Book Canvas Renderer
 */
@Composable
fun OrderBookCanvas(
    marketData: MarketSnapshot,
    webSocket: MarketDataWebSocket,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val textMeasurer = rememberTextMeasurer()
    
    // DVR state - shared with controls
    var isLive by remember { mutableStateOf(true) }
    var replayOffset by remember { mutableStateOf(0) }
    var frozenSequenceId by remember { mutableStateOf(0L) }
    
    // Visual enhancement toggles
    var showHeatmap by remember { mutableStateOf(true) }
    var showDepthCurve by remember { mutableStateOf(true) }
    
    // Zoom and scale controls
    var zoomLevel by remember { mutableStateOf(10) }  // 5, 10, or 20 levels
    var useLogScale by remember { mutableStateOf(false) }  // Linear or logarithmic
    
    // Determine which data to display: live or historical
    val displayData = if (isLive) {
        marketData  // Live mode: always show latest
    } else {
        // Paused mode: fetch from Ring Buffer using absolute sequence ID
        // This ensures the view stays "frozen" even as new data arrives
        remember(replayOffset, frozenSequenceId) {
            val targetId = frozenSequenceId - replayOffset
            webSocket.getHistoryBuffer().getSnapshotByAbsoluteIndex(targetId) ?: marketData
        }
    }
    
    // Monospace font for aligned numbers
    val monoFont = remember { FontFamily.Monospace }
    
    // Pre-allocated colors (OUTSIDE draw loop - zero allocation!)
    val bidColor = remember { Color(0xFF00E676) }  // Neon green
    val askColor = remember { Color(0xFFFF1744) }  // Neon red  
    val textColor = remember { Color(0xFFE6EDF3) } // Off-white
    val bgColor = remember { Color(0xFF0D1117) }   // Rich dark grey
    val gridColor = remember { Color(0xFF30363D) } // Subtle grey
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(16.dp)
    ) {
        // Header with share button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "LiquidityLens - Order Book",
                color = textColor,
                fontSize = 20.sp
            )
            
            // Share snapshot button (viral growth engine!)
            Button(
                onClick = {
                    val view = ComposableCapture.findComposeView(context)
                    view?.let { v ->
                        // Small delay to let UI settle
                        v.postDelayed({
                            val bitmap = ComposableCapture.captureView(v)
                            bitmap?.let { bmp ->
                                SnapshotExporter.shareSnapshot(
                                    context,
                                    bmp,
                                    "Check out this Bitcoin order book snapshot! Real-time liquidity analysis with LiquidityLens."
                                )
                            }
                        }, 100)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00E676) // Neon green
                )
            ) {
                Text("📸 Share", fontSize = 14.sp)
            }
        }
        
        // Market Statistics Panel
        val marketStats = remember(displayData, zoomLevel) {
            calculateMarketStats(displayData, levels = zoomLevel)
        }
        MarketStatsPanel(
            stats = marketStats,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        
        // Visual Controls Panel
        VisualControlsPanel(
            zoomLevel = zoomLevel,
            onZoomLevelChange = { zoomLevel = it },
            useLogScale = useLogScale,
            onLogScaleToggle = { useLogScale = !useLogScale },
            showHeatmap = showHeatmap,
            onHeatmapToggle = { showHeatmap = !showHeatmap },
            showDepthCurve = showDepthCurve,
            onDepthCurveToggle = { showDepthCurve = !showDepthCurve },
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        // Canvas for Order Book
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            
            // Number of levels to display (controlled by zoom)
            val levels = zoomLevel
            val rowHeight = canvasHeight / levels
            
            // Find max volume for scaling bars
            val maxBidVolume = displayData.bidVolumes.take(levels).maxOrNull() ?: 1.0
            val maxAskVolume = displayData.askVolumes.take(levels).maxOrNull() ?: 1.0
            val maxVolume = maxOf(maxBidVolume, maxAskVolume)
            
            // Helper function for volume scaling
            fun scaleVolume(volume: Double, max: Double): Double {
                return if (useLogScale) {
                    // Logarithmic scale: better for wide volume ranges
                    if (volume > 0) kotlin.math.ln(volume + 1) / kotlin.math.ln(max + 1) else 0.0
                } else {
                    // Linear scale
                    volume / max
                }
            }
            
            // Draw center divider
            drawLine(
                color = gridColor,
                start = Offset(canvasWidth / 2, 0f),
                end = Offset(canvasWidth / 2, canvasHeight),
                strokeWidth = 2f
            )
            
            // Draw bid bars (left side, green)
            for (i in 0 until levels) {
                val volume = displayData.bidVolumes[i]
                val price = displayData.bidPrices[i]
                val y = i * rowHeight
                
                // Calculate bar width (scaled to max volume)
                val scaledRatio = scaleVolume(volume, maxVolume)
                val barWidth = scaledRatio * (canvasWidth / 2 - 60)
                
                // Heatmap: Calculate intensity based on volume ratio
                val intensity = if (showHeatmap) {
                    (volume / maxVolume).toFloat().coerceIn(0.2f, 0.8f)
                } else {
                    0.3f  // Default opacity
                }
                
                // Draw volume bar with heatmap
                drawRect(
                    color = bidColor.copy(alpha = intensity),
                    topLeft = Offset(0f, y),
                    size = Size(barWidth.toFloat(), rowHeight - 2)
                )
                
                // Draw price label (monospace for alignment)
                val priceText = String.format("%,10.2f", price)
                drawText(
                    textMeasurer = textMeasurer,
                    text = priceText,
                    topLeft = Offset(10f, y + rowHeight / 3),
                    style = TextStyle(
                        color = bidColor,
                        fontSize = 11.sp,
                        fontFamily = monoFont,
                        fontWeight = FontWeight.Bold
                    )
                )
                
                // Volume label
                val volumeText = String.format("%8.4f", volume)
                drawText(
                    textMeasurer = textMeasurer,
                    text = volumeText,
                    topLeft = Offset(canvasWidth / 2 - 90, y + rowHeight / 3),
                    style = TextStyle(
                        color = textColor.copy(alpha = 0.7f),
                        fontSize = 10.sp,
                        fontFamily = monoFont
                    )
                )
            }
            
            // Draw ask bars (right side, red)
            for (i in 0 until levels) {
                val volume = displayData.askVolumes[i]
                val price = displayData.askPrices[i]
                val y = i * rowHeight
                
                // Calculate bar width (scaled to max volume)
                val scaledAskRatio = scaleVolume(volume, maxVolume)
                val barWidth = scaledAskRatio * (canvasWidth / 2 - 60)
                
                // Heatmap: Calculate intensity based on volume ratio
                val askIntensity = if (showHeatmap) {
                    (volume / maxVolume).toFloat().coerceIn(0.2f, 0.8f)
                } else {
                    0.3f  // Default opacity
                }
                
                // Draw volume bar (starting from center, going right)
                drawRect(
                    color = askColor.copy(alpha = askIntensity),
                    topLeft = Offset(canvasWidth / 2 + 2, y),
                    size = Size(barWidth.toFloat(), rowHeight - 2)
                )
                
                // Draw price label (monospace for alignment)
                val priceText = String.format("%,10.2f", price)
                drawText(
                    textMeasurer = textMeasurer,
                    text = priceText,
                    topLeft = Offset(canvasWidth / 2 + 10, y + rowHeight / 3),
                    style = TextStyle(
                        color = askColor,
                        fontSize = 11.sp,
                        fontFamily = monoFont,
                        fontWeight = FontWeight.Bold
                    )
                )
                
                // Draw volume label (monospace)
                val volumeText = String.format("%8.4f", volume)
                drawText(
                    textMeasurer = textMeasurer,
                    text = volumeText,
                    topLeft = Offset(canvasWidth - 90, y + rowHeight / 3),
                    style = TextStyle(
                        color = textColor.copy(alpha = 0.7f),
                        fontSize = 10.sp,
                        fontFamily = monoFont
                    )
                )
            }
            
            // Depth Curve Visualization (Cumulative)
            if (showDepthCurve) {
                // Calculate cumulative depth for bids
                val bidCumulativeDepth = mutableListOf<Double>()
                var bidSum = 0.0
                for (i in 0 until levels) {
                    bidSum += displayData.bidVolumes[i]
                    bidCumulativeDepth.add(bidSum)
                }
                
                // Calculate cumulative depth for asks
                val askCumulativeDepth = mutableListOf<Double>()
                var askSum = 0.0
                for (i in 0 until levels) {
                    askSum += displayData.askVolumes[i]
                    askCumulativeDepth.add(askSum)
                }
                
                val maxCumulativeDepth = maxOf(bidSum, askSum)
                
                // Draw bid depth curve (left side)
                for (i in 0 until levels - 1) {
                    val depth1 = bidCumulativeDepth[i]
                    val depth2 = bidCumulativeDepth[i + 1]
                    val x1 = ((depth1 / maxCumulativeDepth) * (canvasWidth / 2 - 60)).toFloat()
                    val x2 = ((depth2 / maxCumulativeDepth) * (canvasWidth / 2 - 60)).toFloat()
                    val y1 = i * rowHeight + rowHeight / 2
                    val y2 = (i + 1) * rowHeight + rowHeight / 2
                    
                    drawLine(
                        color = bidColor,
                        start = Offset(x1, y1),
                        end = Offset(x2, y2),
                        strokeWidth = 3f
                    )
                }
                
                // Draw ask depth curve (right side)
                for (i in 0 until levels - 1) {
                    val depth1 = askCumulativeDepth[i]
                    val depth2 = askCumulativeDepth[i + 1]
                    val x1 = canvasWidth / 2 + 2 + ((depth1 / maxCumulativeDepth) * (canvasWidth / 2 - 60)).toFloat()
                    val x2 = canvasWidth / 2 + 2 + ((depth2 / maxCumulativeDepth) * (canvasWidth / 2 - 60)).toFloat()
                    val y1 = i * rowHeight + rowHeight / 2
                    val y2 = (i + 1) * rowHeight + rowHeight / 2
                    
                    drawLine(
                        color = askColor,
                        start = Offset(x1, y1),
                        end = Offset(x2, y2),
                        strokeWidth = 3f
                    )
                }
            }
        }
        
        // Market DVR Controls
        MarketDVRControls(
            webSocket = webSocket,
            marketData = displayData, // Pass the DISPLAYED snapshot, not the live one
            textColor = textColor,
            isLive = isLive,
            onLiveChanged = { live -> 
                isLive = live
                if (!live) {
                    // Capture current sequence ID when pausing to freeze the view
                    frozenSequenceId = webSocket.getHistoryBuffer().getTotalWrites() - 1
                    replayOffset = 0
                }
            },
            replayOffset = replayOffset,
            onReplayOffsetChanged = { replayOffset = it },
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

/**
 * Market DVR Controls - Time travel through market history
 */
@Composable
fun MarketDVRControls(
    webSocket: MarketDataWebSocket,
    marketData: MarketSnapshot,
    textColor: Color,
    isLive: Boolean,
    onLiveChanged: (Boolean) -> Unit,
    replayOffset: Int,
    onReplayOffsetChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val historyBuffer = webSocket.getHistoryBuffer()
    val bufferSize = historyBuffer.getCurrentSize()
    
    Column(modifier = modifier.fillMaxWidth()) {
        // Live/Replay indicator
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isLive) "🔴 LIVE" else "⏸️ REPLAY",
                color = if (isLive) Color(0xFFFF1744) else Color(0xFFFFB74D),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            
            Button(
                onClick = {
                    onLiveChanged(!isLive)
                    if (!isLive) onReplayOffsetChanged(0)  // Reset offset when going live
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isLive) Color(0xFF30363D) else Color(0xFF00E676)
                )
            ) {
                Text(if (isLive) "Pause" else "Go Live")
            }
        }
        
        // Replay slider (only shown when paused AND buffer has enough data)
        if (!isLive && bufferSize > 50) {  // Minimum 50 snapshots (5 seconds) for meaningful replay
            Spacer(modifier = Modifier.height(12.dp))
            
            // Current position indicator with millisecond precision
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    // Get timestamp (live or historical)
                    val (hours, minutes, seconds, millis) = remember(replayOffset, isLive, marketData) {
                        val snapshot = if (isLive) marketData else webSocket.getHistoryBuffer().getSnapshot(replayOffset)
                        snapshot?.timestamp?.let { timestamp ->
                            val date = java.util.Date(timestamp)
                            val cal = java.util.Calendar.getInstance().apply { time = date }
                            val h = cal.get(java.util.Calendar.HOUR_OF_DAY)
                            val m = cal.get(java.util.Calendar.MINUTE)
                            val s = cal.get(java.util.Calendar.SECOND)
                            val ms = cal.get(java.util.Calendar.MILLISECOND)
                            listOf(
                                String.format("%02d", h),
                                String.format("%02d", m),
                                String.format("%02d", s),
                                String.format("%03d", ms)
                            )
                        } ?: listOf("--", "--", "--", "---")
                    }
                    
                    // Animation for pulsing effect
                    val infiniteTransition = rememberInfiniteTransition()
                    val pulseAlpha by infiniteTransition.animateFloat(
                        initialValue = 1f,
                        targetValue = 0.3f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(800),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "Pulse Animation"
                    )

                    // Cyberpunk-styled timestamp with color coding (COMPACT VERSION)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
                    ) {
                        // HH
                        Text(
                            text = hours,
                            color = if (isLive) Color(0xFFFF1744) else Color(0xFF00E676),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = ":",
                            color = Color(0xFF30363D),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        
                        // MM
                        Text(
                            text = minutes,
                            color = if (isLive) Color(0xFFFF1744) else Color(0xFF00E676),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = ":",
                            color = Color(0xFF30363D),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        
                        // SS
                        Text(
                            text = seconds,
                            color = if (isLive) Color(0xFFFF1744) else Color(0xFF00E676),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = ".",
                            color = Color(0xFF58A6FF),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        
                        // MSS (milliseconds - minor)
                        Text(
                            text = millis,
                            color = Color(0xFF58A6FF),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                        
                        Spacer(modifier = Modifier.width(8.dp))

                        // Live/Replay Indicator (Moved to end)
                        if (isLive) {
                            Text(
                                text = "🔴 LIVE",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFF1744).copy(alpha = pulseAlpha),
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        } else {
                            Text(
                                text = "⏰ REPLAY",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF00E676),
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                        
                        Spacer(modifier = Modifier.width(8.dp))
                        
                        // System Time (Compact inline)
                        val systemTime = remember(System.currentTimeMillis()) {
                            java.text.SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault())
                                .format(java.util.Date())
                        }
                        Text(
                            text = "Now: $systemTime",
                            color = Color.Gray,
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                    
                    // Frame info (Compact)
                    if (!isLive) {
                        Text(
                            text = "Frame #$replayOffset (~${replayOffset / 10}s ago)",
                            color = Color(0xFFFFB74D),
                            fontSize = 10.sp
                        )
                    }
                }
                
                
                
                
                Text(
                    text = "Max: ${bufferSize / 10}s",
                    color = textColor.copy(alpha = 0.6f),
                    fontSize = 11.sp
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Frame-by-frame navigation buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Jump -10s button
                Button(
                    onClick = {
                        val newOffset = (replayOffset + 100).coerceAtMost(bufferSize - 1)
                        onReplayOffsetChanged(newOffset)
                    },
                    enabled = replayOffset < bufferSize - 100,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF30363D),
                        disabledContainerColor = Color(0xFF21262D)
                    ),
                    modifier = Modifier.weight(1f).padding(horizontal = 2.dp)
                ) {
                    Text("⏮ -10s", fontSize = 11.sp)
                }
                
                // Previous frame button
                Button(
                    onClick = {
                        onReplayOffsetChanged((replayOffset + 1).coerceAtMost(bufferSize - 1))
                    },
                    enabled = replayOffset < bufferSize - 1,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF30363D),
                        disabledContainerColor = Color(0xFF21262D)
                    ),
                    modifier = Modifier.weight(1f).padding(horizontal = 2.dp)
                ) {
                    Text("◀ Prev", fontSize = 11.sp)
                }
                
                // Next frame button
                Button(
                    onClick = {
                        onReplayOffsetChanged((replayOffset - 1).coerceAtLeast(0))
                    },
                    enabled = replayOffset > 0,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF30363D),
                        disabledContainerColor = Color(0xFF21262D)
                    ),
                    modifier = Modifier.weight(1f).padding(horizontal = 2.dp)
                ) {
                    Text("Next ▶", fontSize = 11.sp)
                }
                
                // Jump +10s button
                Button(
                    onClick = {
                        val newOffset = (replayOffset - 100).coerceAtLeast(0)
                        onReplayOffsetChanged(newOffset)
                    },
                    enabled = replayOffset >= 100,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF30363D),
                        disabledContainerColor = Color(0xFF21262D)
                    ),
                    modifier = Modifier.weight(1f).padding(horizontal = 2.dp)
                ) {
                    Text("+10s ⏭", fontSize = 11.sp)
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Slider for quick scrubbing
            Text(
                text = "Quick Scrub:",
                color = textColor.copy(alpha = 0.7f),
                fontSize = 10.sp
            )
            
            Slider(
                value = replayOffset.toFloat(),
                onValueChange = { onReplayOffsetChanged(it.toInt()) },
                valueRange = 0f..(bufferSize - 1).toFloat(),
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xFF00E676),
                    activeTrackColor = Color(0xFF00E676),
                    inactiveTrackColor = Color(0xFF30363D)
                )
            )
            
            // Time range indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Now",
                    color = textColor.copy(alpha = 0.5f),
                    fontSize = 9.sp
                )
                Text(
                    text = "${bufferSize / 10}s ago",
                    color = textColor.copy(alpha = 0.5f),
                    fontSize = 9.sp
                )
            }
        } else if (!isLive && bufferSize <= 50) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "⏳ Building buffer... need 50+ snapshots (${bufferSize}/50)",
                color = Color(0xFFFFB74D),
                fontSize = 11.sp
            )
        }
        
        // Buffer status
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Buffer: $bufferSize / ${historyBuffer.getCapacity()}",
                color = textColor.copy(alpha = 0.5f),
                fontSize = 10.sp
            )
            Text(
                text = "~${bufferSize * 640 / 1024} KB",
                color = textColor.copy(alpha = 0.5f),
                fontSize = 10.sp
            )
        }
    }
}
