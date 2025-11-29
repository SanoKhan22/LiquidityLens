package com.liquiditylens.ui.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.liquiditylens.data.model.Timeframe

/**
 * Chart Screen - Main candlestick chart view
 * 
 * Phase 1: Basic UI with real-time WebSocket connection
 * Phase 2: MPAndroidChart integration (next step)
 */
@Composable
fun ChartScreen(
    viewModel: ChartViewModel = viewModel()
) {
    val chartState by viewModel.chartState.collectAsState()
    
    val bgColor = Color(0xFF0D1117)
    val textColor = Color(0xFFE6EDF3)
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
    ) {
        // Header with symbol and price
        ChartHeader(
            symbol = chartState.symbol,
            lastPrice = chartState.lastPrice,
            textColor = textColor
        )
        
        // Timeframe selector
        TimeframeSelector(
            currentTimeframe = Timeframe.fromValue(chartState.interval),
            onTimeframeSelect = { viewModel.changeTimeframe(it) },
            textColor = textColor
        )
        
        // Chart area (placeholder for now)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (chartState.isLoading) {
                CircularProgressIndicator(color = Color(0xFF00E676))
            } else if (chartState.candles.isNotEmpty()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "📈 Chart Ready",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00E676)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${chartState.candles.size} candles loaded",
                        fontSize = 14.sp,
                        color = textColor.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Latest: $${String.format("%.2f", chartState.lastPrice ?: 0.0)}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF58A6FF)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "MPAndroidChart integration coming next...",
                        fontSize = 12.sp,
                        color = textColor.copy(alpha = 0.5f)
                    )
                }
            } else {
                Text(
                    text = "No data available",
                    fontSize = 16.sp,
                    color = textColor.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Composable
fun ChartHeader(
    symbol: String,
    lastPrice: Double?,
    textColor: Color
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color(0xFF161B22)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = symbol,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
            lastPrice?.let { price ->
                Text(
                    text = "$${String.format("%,.2f", price)}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF00E676)
                )
            }
        }
    }
}

@Composable
fun TimeframeSelector(
    currentTimeframe: Timeframe,
    onTimeframeSelect: (Timeframe) -> Unit,
    textColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Timeframe.values().forEach { timeframe ->
            Button(
                onClick = { onTimeframeSelect(timeframe) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (timeframe == currentTimeframe) 
                        Color(0xFF00E676) 
                    else 
                        Color(0xFF30363D),
                    contentColor = if (timeframe == currentTimeframe) 
                        Color.Black 
                    else 
                        textColor
                ),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                modifier = Modifier.height(32.dp)
            ) {
                Text(
                    text = timeframe.displayName,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
