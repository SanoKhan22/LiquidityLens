package com.liquiditylens.ui.chart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liquiditylens.data.model.Candle
import com.liquiditylens.data.model.ChartState
import com.liquiditylens.data.model.Timeframe
import com.liquiditylens.data.websocket.BinanceKlineWebSocket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for Chart Screen
 * 
 * Manages chart state, real-time WebSocket connection, and candle data.
 */
class ChartViewModel : ViewModel() {
    
    private val _chartState = MutableStateFlow(ChartState())
    val chartState: StateFlow<ChartState> = _chartState.asStateFlow()
    
    private var klineWebSocket: BinanceKlineWebSocket? = null
    
    init {
        // Start with default symbol and interval
        connect("btcusdt", "1m")
    }
    
    /**
     * Connect to WebSocket for a specific symbol and timeframe
     */
    fun connect(symbol: String, interval: String) {
        // Disconnect existing connection
        klineWebSocket?.disconnect()
        
        // Update state
        _chartState.value = _chartState.value.copy(
            symbol = symbol.uppercase(),
            interval = interval,
            isLoading = true
        )
        
        // Create new WebSocket connection
        klineWebSocket = BinanceKlineWebSocket(symbol, interval)
        
        // Collect candle updates
        viewModelScope.launch {
            klineWebSocket!!.candle.collect { candle ->
                candle?.let { updateCandle(it) }
            }
        }
        
        // Collect connection state
        viewModelScope.launch {
            klineWebSocket!!.connectionState.collect { state ->
                // Handle connection state changes
                _chartState.value = _chartState.value.copy(
                    isLoading = state != com.liquiditylens.data.websocket.WebSocketState.CONNECTED
                )
            }
        }
        
        // Connect to WebSocket
        klineWebSocket?.connect()
    }
    
    /**
     * Update or append candle to the list
     */
    private fun updateCandle(newCandle: Candle) {
        val currentCandles = _chartState.value.candles.toMutableList()
        
        // Find if candle already exists
        val existingIndex = currentCandles.indexOfFirst { it.id == newCandle.id }
        
        if (existingIndex != -1) {
            // Update existing candle
            currentCandles[existingIndex] = newCandle
        } else {
            // Append new candle
            currentCandles.add(newCandle)
            
            // Keep only last 200 candles (optimization)
            if (currentCandles.size > 200) {
                currentCandles.removeAt(0)
            }
        }
        
        // Update state
        _chartState.value = _chartState.value.copy(
            candles = currentCandles,
            lastPrice = newCandle.close
        )
    }
    
    /**
     * Change timeframe
     */
    fun changeTimeframe(timeframe: Timeframe) {
        connect(_chartState.value.symbol.lowercase(), timeframe.value)
    }
    
    /**
     * Change symbol
     */
    fun changeSymbol(symbol: String) {
        connect(symbol.lowercase(), _chartState.value.interval)
    }
    
    override fun onCleared() {
        super.onCleared()
        klineWebSocket?.disconnect()
    }
}
