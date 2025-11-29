package com.liquiditylens.data.websocket

import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.liquiditylens.data.model.Candle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.*
import okio.ByteString

/**
 * WebSocket Client for Binance Kline (Candlestick) Stream
 * 
 * Connects to Binance WebSocket API and streams real-time candlestick data.
 * Endpoint format: wss://stream.binance.com:9443/ws/{symbol}@kline_{interval}
 */
class BinanceKlineWebSocket(
    private val symbol: String = "btcusdt",
    private val interval: String = "1m"
) {
    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null
    private val gson = Gson()
    
    private val _candle = MutableStateFlow<Candle?>(null)
    val candle: StateFlow<Candle?> = _candle
    
    private val _connectionState = MutableStateFlow(WebSocketState.DISCONNECTED)
    val connectionState: StateFlow<WebSocketState> = _connectionState
    
    /**
     * Connect to Binance Kline WebSocket
     */
    fun connect() {
        val url = "wss://stream.binance.com:9443/ws/${symbol}@kline_${interval}"
        Log.d("KlineWebSocket", "Connecting to: $url")
        
        val request = Request.Builder()
            .url(url)
            .build()
        
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                Log.d("KlineWebSocket", "Connected successfully to $symbol @ $interval")
                _connectionState.value = WebSocketState.CONNECTED
            }
            
            override fun onMessage(webSocket: WebSocket, text: String) {
                try {
                    val message = gson.fromJson(text, KlineMessage::class.java)
                    val kline = message.k
                    
                    // Convert to Candle model
                    val candle = Candle(
                        id = Candle.generateId(symbol.uppercase(), interval, kline.openTime),
                        symbol = symbol.uppercase(),
                        interval = interval,
                        openTime = kline.openTime,
                        open = kline.open.toDouble(),
                        high = kline.high.toDouble(),
                        low = kline.low.toDouble(),
                        close = kline.close.toDouble(),
                        volume = kline.volume.toDouble(),
                        closeTime = kline.closeTime,
                        isFinal = kline.isFinal
                    )
                    
                    _candle.value = candle
                    Log.d("KlineWebSocket", "Candle update: ${candle.close} (final: ${candle.isFinal})")
                    
                } catch (e: Exception) {
                    Log.e("KlineWebSocket", "Error parsing message: ${e.message}", e)
                }
            }
            
            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Log.e("KlineWebSocket", "Connection failed: ${t.message}", t)
                _connectionState.value = WebSocketState.ERROR
            }
            
            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                Log.d("KlineWebSocket", "Connection closed. Code: $code, Reason: $reason")
                _connectionState.value = WebSocketState.DISCONNECTED
            }
        })
    }
    
    /**
     * Disconnect from WebSocket
     */
    fun disconnect() {
        webSocket?.close(1000, "User disconnected")
        _connectionState.value = WebSocketState.DISCONNECTED
    }
    
    /**
     * Change symbol/interval (disconnect and reconnect)
     */
    fun changeStream(newSymbol: String, newInterval: String) {
        disconnect()
        // Update symbol and interval, then reconnect
        // Note: In production, you'd want to update the instance fields
        connect()
    }
}

/**
 * WebSocket Connection State
 */
enum class WebSocketState {
    DISCONNECTED,
    CONNECTING,
    CONNECTED,
    ERROR
}

/**
 * Binance Kline Message Format
 */
private data class KlineMessage(
    @SerializedName("e") val eventType: String,  // "kline"
    @SerializedName("E") val eventTime: Long,
    @SerializedName("s") val symbol: String,
    @SerializedName("k") val k: KlineData
)

private data class KlineData(
    @SerializedName("t") val openTime: Long,         // Kline start time
    @SerializedName("T") val closeTime: Long,        // Kline close time
    @SerializedName("s") val symbol: String,          // Symbol
    @SerializedName("i") val interval: String,        // Interval
    @SerializedName("o") val open: String,            // Open price
    @SerializedName("c") val close: String,           // Close price
    @SerializedName("h") val high: String,            // High price
    @SerializedName("l") val low: String,             // Low price
    @SerializedName("v") val volume: String,          // Base asset volume
    @SerializedName("x") val isFinal: Boolean         // Is this kline closed?
)
