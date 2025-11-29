package com.liquiditylens.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Settings Screen
 */
@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val bgColor = Color(0xFF0D1117)
    val textColor = Color(0xFFE6EDF3)
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "⚙️ Settings",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Coming soon: Theme, notifications, preferences",
            fontSize = 14.sp,
            color = textColor.copy(alpha = 0.6f)
        )
    }
}
