package com.liquiditylens.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Visual Controls Panel
 * 
 * Provides controls for zoom level, volume scaling, and visual enhancements.
 */
@Composable
fun VisualControlsPanel(
    zoomLevel: Int,
    onZoomLevelChange: (Int) -> Unit,
    useLogScale: Boolean,
    onLogScaleToggle: () -> Unit,
    showHeatmap: Boolean,
    onHeatmapToggle: () -> Unit,
    showDepthCurve: Boolean,
    onDepthCurveToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bgColor = Color(0xFF161B22)
    val textColor = Color(0xFFE6EDF3)
    val accentColor = Color(0xFF58A6FF)
    
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Header
            Text(
                text = "⚙️ Display Controls",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = textColor.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            // Zoom Level Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Zoom:",
                    fontSize = 11.sp,
                    color = textColor.copy(alpha = 0.8f),
                    fontFamily = FontFamily.Monospace
                )
                
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    ZoomButton(
                        text = "5",
                        isSelected = zoomLevel == 5,
                        onClick = { onZoomLevelChange(5) }
                    )
                    ZoomButton(
                        text = "10",
                        isSelected = zoomLevel == 10,
                        onClick = { onZoomLevelChange(10) }
                    )
                    ZoomButton(
                        text = "20",
                        isSelected = zoomLevel == 20,
                        onClick = { onZoomLevelChange(20) }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Scale and Visual Toggle Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Linear/Log Scale Toggle
                ToggleButton(
                    text = if (useLogScale) "Log" else "Linear",
                    isActive = useLogScale,
                    onClick = onLogScaleToggle,
                    modifier = Modifier.weight(1f).padding(horizontal = 2.dp)
                )
                
                // Heatmap Toggle
                ToggleButton(
                    text = "Heat",
                    isActive = showHeatmap,
                    onClick = onHeatmapToggle,
                    modifier = Modifier.weight(1f).padding(horizontal = 2.dp)
                )
                
                // Depth Curve Toggle
                ToggleButton(
                    text = "Depth",
                    isActive = showDepthCurve,
                    onClick = onDepthCurveToggle,
                    modifier = Modifier.weight(1f).padding(horizontal = 2.dp)
                )
            }
        }
    }
}

@Composable
private fun ZoomButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF00E676) else Color(0xFF30363D),
            contentColor = if (isSelected) Color.Black else Color(0xFFE6EDF3)
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
        modifier = Modifier.height(30.dp)
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
private fun ToggleButton(
    text: String,
    isActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isActive) Color(0xFF58A6FF) else Color(0xFF30363D),
            contentColor = if (isActive) Color.Black else Color(0xFFE6EDF3)
        ),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
        modifier = modifier.height(30.dp)
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
    }
}
