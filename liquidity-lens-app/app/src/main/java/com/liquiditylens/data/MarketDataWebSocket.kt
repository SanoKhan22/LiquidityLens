package com.liquiditylens.data

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.*
import okio.ByteString
import java.nio.ByteBuffer

/**
 * WebSocket Client for receiving binary market data from backend.
 * 
 * Protocol: Receives 640-byte frames containing:
 * - 20 bid prices (double)
 * - 20 bid volumes (double)
 * - 20 ask prices (double)
 * - 20 ask volumes (double)
 */
class MarketDataWebSocket(
    private val serverUrl: String = "ws://192.168.0.225:8080/market-feed"
) {
    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null
    
    // Ring Buffer for Market DVR functionality
    private val historyBuffer = MarketHistoryBuffer(capacity = 3000)
    
    private val _marketData = MutableStateFlow<MarketSnapshot?>(null)
    val marketData: StateFlow<MarketSnapshot?> = _marketData
    
    private val _connectionState = MutableStateFlow(ConnectionState.DISCONNECTED)
    val connectionState: StateFlow<ConnectionState> = _connectionState
    
    /**
     * Get Ring Buffer for replay/history features.
     */
    fun getHistoryBuffer(): MarketHistoryBuffer = historyBuffer
    
    fun connect() {
        Log.d("WebSocket", "Attempting to connect to: $serverUrl")
        val request = Request.Builder()
            .url(serverUrl)
            .build()
            
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                Log.d("WebSocket", "Connected successfully!")
                _connectionState.value = ConnectionState.CONNECTED
            }
            
            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                Log.d("WebSocket", "Received binary message: ${bytes.size} bytes")
                // Parse binary data (640 bytes)
                val buffer = ByteBuffer.wrap(bytes.toByteArray())
                
                val bidPrices = DoubleArray(20) { buffer.double }
                val bidVolumes = DoubleArray(20) { buffer.double }
                val askPrices = DoubleArray(20) { buffer.double }
                val askVolumes = DoubleArray(20) { buffer.double }
                
                val snapshot = MarketSnapshot(
                    bidPrices, bidVolumes,
                    askPrices, askVolumes,
                    System.currentTimeMillis()
                )
                
                // Store in Ring Buffer (Market DVR)
                historyBuffer.add(snapshot)
                
                // Emit to UI
                _marketData.value = snapshot
            }
            
            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Log.e("WebSocket", "Connection failed: ${t.message}", t)
                Log.e("WebSocket", "Response: $response")
                _connectionState.value = ConnectionState.ERROR
            }
            
            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                Log.d("WebSocket", "Connection closed. Code: $code, Reason: $reason")
                _connectionState.value = ConnectionState.DISCONNECTED
            }
        })
    }
    
    fun disconnect() {
        webSocket?.close(1000, "User disconnected")
    }
}

data class MarketSnapshot(
    val bidPrices: DoubleArray,
    val bidVolumes: DoubleArray,
    val askPrices: DoubleArray,
    val askVolumes: DoubleArray,
    val timestamp: Long
)

enum class ConnectionState {
    DISCONNECTED, CONNECTING, CONNECTED, ERROR
}
