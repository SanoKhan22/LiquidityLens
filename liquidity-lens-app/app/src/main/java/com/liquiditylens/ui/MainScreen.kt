package com.liquiditylens.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.liquiditylens.data.ConnectionState
import com.liquiditylens.data.MarketDataWebSocket
import com.liquiditylens.data.MarketSnapshot

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    webSocket: MarketDataWebSocket,
    marketData: MarketSnapshot?,
    connectionState: ConnectionState
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "LiquidityLens",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE6EDF3)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF0D1117),
                    titleContentColor = Color(0xFFE6EDF3)
                ),
                actions = {
                    // Connection Status Pill
                    Surface(
                        color = if (connectionState == ConnectionState.CONNECTED) 
                            Color(0xFF238636).copy(alpha = 0.2f) 
                        else 
                            Color(0xFFDA3633).copy(alpha = 0.2f),
                        shape = CircleShape,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .background(
                                        if (connectionState == ConnectionState.CONNECTED) 
                                            Color(0xFF238636) 
                                        else 
                                            Color(0xFFDA3633),
                                        CircleShape
                                    )
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (connectionState == ConnectionState.CONNECTED) "LINKED" else "OFFLINE",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (connectionState == ConnectionState.CONNECTED) 
                                    Color(0xFF3FB950) 
                                else 
                                    Color(0xFFF85149)
                            )
                        }
                    }
                    
                    IconButton(onClick = { /* TODO: Settings */ }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Color(0xFF8B949E)
                        )
                    }
                }
            )
        },
        containerColor = Color(0xFF0D1117)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (marketData != null) {
                OrderBookCanvas(
                    marketData = marketData,
                    webSocket = webSocket,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                // Loading State
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF238636))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Connecting to Market Feed...",
                        color = Color(0xFF8B949E),
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
    }
}
