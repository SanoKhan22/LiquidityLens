package com.liquiditylens.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.liquiditylens.data.ConnectionState
import com.liquiditylens.data.MarketDataWebSocket

class MainActivity : ComponentActivity() {
    private val orderBookWebSocket = MarketDataWebSocket()  // For order book only
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            MaterialTheme {
                // Connect order book WebSocket
                LaunchedEffect(Unit) {
                    orderBookWebSocket.connect()
                }
                
                // Use new navigation structure
                com.liquiditylens.ui.navigation.LiquidityLensApp(
                    orderBookWebSocket = orderBookWebSocket
                )
            }
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        orderBookWebSocket.disconnect()
    }
}
