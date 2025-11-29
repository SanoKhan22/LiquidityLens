package com.liquiditylens.ui.orderbook

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.liquiditylens.data.MarketDataWebSocket
import com.liquiditylens.ui.MainScreen

/**
 * Order Book Screen (Wrapper for existing OrderBookCanvas)
 */
@Composable
fun OrderBookScreen(
    webSocket: MarketDataWebSocket,
    modifier: Modifier = Modifier
) {
    val marketData by webSocket.marketData.collectAsState()
    val connectionState by webSocket.connectionState.collectAsState()
    
    // Reuse existing MainScreen which contains OrderBookCanvas
    marketData?.let { data ->
        MainScreen(
            webSocket = webSocket,
            marketData = data,
            connectionState = connectionState
        )
    }
}
