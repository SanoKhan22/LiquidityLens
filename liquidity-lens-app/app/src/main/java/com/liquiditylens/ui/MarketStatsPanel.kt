package com.liquiditylens.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.liquiditylens.data.MarketStats
import kotlin.math.abs

/**
 * Market Statistics Panel
 * 
 * Displays key market metrics in a compact, cyberpunk-styled panel.
 */
@Composable
fun MarketStatsPanel(
    stats: MarketStats,
    modifier: Modifier = Modifier
) {
    val bgColor = Color(0xFF0D1117)
    val cardBg = Color(0xFF161B22)
    val borderColor = Color(0xFF30363D)
    val textColor = Color(0xFFE6EDF3)
    val bidColor = Color(0xFF00E676)
    val askColor = Color(0xFFFF1744)
    
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Header
            Text(
                text = "📊 Market Stats",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = textColor.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            // Top Row: Mid-Price and Spread
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Mid-Price (Prominent)
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "MID PRICE",
                        fontSize = 9.sp,
                        color = textColor.copy(alpha = 0.5f),
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = "$${String.format("%,.2f", stats.midPrice)}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF58A6FF),
                        fontFamily = FontFamily.Monospace
                    )
                }
                
                // Spread
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "SPREAD",
                        fontSize = 9.sp,
                        color = textColor.copy(alpha = 0.5f),
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = "$${String.format("%.2f", stats.spread)}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = when {
                            stats.spreadPercent < 0.01 -> bidColor  // Tight spread
                            stats.spreadPercent < 0.05 -> Color(0xFFFFB74D)  // Normal
                            else -> askColor  // Wide spread
                        },
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = "${String.format("%.3f", stats.spreadPercent)}%",
                        fontSize = 9.sp,
                        color = textColor.copy(alpha = 0.6f),
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Order Book Imbalance Bar
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "ORDER BOOK IMBALANCE",
                        fontSize = 9.sp,
                        color = textColor.copy(alpha = 0.5f),
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = "${String.format("%+.1f", stats.imbalancePercent)}%",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (stats.imbalancePercent > 0) bidColor else askColor,
                        fontFamily = FontFamily.Monospace
                    )
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                // Visual imbalance bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .background(borderColor, RoundedCornerShape(3.dp))
                ) {
                    // Normalized imbalance (-100 to +100 → 0 to 1)
                    val normalizedImbalance = (stats.imbalancePercent + 100) / 200
                    val clampedImbalance = normalizedImbalance.coerceIn(0.0, 1.0)
                    
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(clampedImbalance.toFloat())
                            .fillMaxHeight()
                            .background(
                                if (stats.imbalancePercent > 0) bidColor else askColor,
                                RoundedCornerShape(3.dp)
                            )
                    )
                    
                    // Center line (50% mark)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight()
                            .background(Color.Transparent)
                    ) {
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .fillMaxHeight()
                                .align(Alignment.CenterEnd)
                                .background(textColor.copy(alpha = 0.3f))
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Bottom Row: Volume totals
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Bid Volume
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "BID VOL",
                        fontSize = 9.sp,
                        color = bidColor.copy(alpha = 0.7f),
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = String.format("%.2f", stats.totalBidVolume),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = bidColor,
                        fontFamily = FontFamily.Monospace
                    )
                }
                
                // Ratio
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "RATIO",
                        fontSize = 9.sp,
                        color = textColor.copy(alpha = 0.5f),
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = String.format("%.2f", stats.bidAskRatio),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor,
                        fontFamily = FontFamily.Monospace
                    )
                }
                
                // Ask Volume
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "ASK VOL",
                        fontSize = 9.sp,
                        color = askColor.copy(alpha = 0.7f),
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = String.format("%.2f", stats.totalAskVolume),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = askColor,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
    }
}
