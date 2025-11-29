package com.liquiditylens.data.websocket;

/**
 * WebSocket Client for Binance Kline (Candlestick) Stream
 *
 * Connects to Binance WebSocket API and streams real-time candlestick data.
 * Endpoint format: wss://stream.binance.com:9443/ws/{symbol}@kline_{interval}
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003J\u0006\u0010\u001b\u001a\u00020\u0018J\u0006\u0010\u001c\u001a\u00020\u0018R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/liquiditylens/data/websocket/BinanceKlineWebSocket;", "", "symbol", "", "interval", "(Ljava/lang/String;Ljava/lang/String;)V", "_candle", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/liquiditylens/data/model/Candle;", "_connectionState", "Lcom/liquiditylens/data/websocket/WebSocketState;", "candle", "Lkotlinx/coroutines/flow/StateFlow;", "getCandle", "()Lkotlinx/coroutines/flow/StateFlow;", "client", "Lokhttp3/OkHttpClient;", "connectionState", "getConnectionState", "gson", "Lcom/google/gson/Gson;", "webSocket", "Lokhttp3/WebSocket;", "changeStream", "", "newSymbol", "newInterval", "connect", "disconnect", "app_debug"})
public final class BinanceKlineWebSocket {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String symbol = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String interval = null;
    @org.jetbrains.annotations.NotNull()
    private final okhttp3.OkHttpClient client = null;
    @org.jetbrains.annotations.Nullable()
    private okhttp3.WebSocket webSocket;
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.liquiditylens.data.model.Candle> _candle = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.liquiditylens.data.model.Candle> candle = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.liquiditylens.data.websocket.WebSocketState> _connectionState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.liquiditylens.data.websocket.WebSocketState> connectionState = null;
    
    public BinanceKlineWebSocket(@org.jetbrains.annotations.NotNull()
    java.lang.String symbol, @org.jetbrains.annotations.NotNull()
    java.lang.String interval) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.liquiditylens.data.model.Candle> getCandle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.liquiditylens.data.websocket.WebSocketState> getConnectionState() {
        return null;
    }
    
    /**
     * Connect to Binance Kline WebSocket
     */
    public final void connect() {
    }
    
    /**
     * Disconnect from WebSocket
     */
    public final void disconnect() {
    }
    
    /**
     * Change symbol/interval (disconnect and reconnect)
     */
    public final void changeStream(@org.jetbrains.annotations.NotNull()
    java.lang.String newSymbol, @org.jetbrains.annotations.NotNull()
    java.lang.String newInterval) {
    }
    
    public BinanceKlineWebSocket() {
        super();
    }
}