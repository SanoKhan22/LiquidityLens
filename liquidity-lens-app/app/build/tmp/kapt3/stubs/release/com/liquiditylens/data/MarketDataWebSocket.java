package com.liquiditylens.data;

/**
 * WebSocket Client for receiving binary market data from backend.
 *
 * Protocol: Receives 640-byte frames containing:
 * - 20 bid prices (double)
 * - 20 bid volumes (double)
 * - 20 ask prices (double)
 * - 20 ask volumes (double)
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0017J\u0006\u0010\u0019\u001a\u00020\u0011R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/liquiditylens/data/MarketDataWebSocket;", "", "serverUrl", "", "(Ljava/lang/String;)V", "_connectionState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/liquiditylens/data/ConnectionState;", "_marketData", "Lcom/liquiditylens/data/MarketSnapshot;", "client", "Lokhttp3/OkHttpClient;", "connectionState", "Lkotlinx/coroutines/flow/StateFlow;", "getConnectionState", "()Lkotlinx/coroutines/flow/StateFlow;", "historyBuffer", "Lcom/liquiditylens/data/MarketHistoryBuffer;", "marketData", "getMarketData", "webSocket", "Lokhttp3/WebSocket;", "connect", "", "disconnect", "getHistoryBuffer", "app_release"})
public final class MarketDataWebSocket {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String serverUrl = null;
    @org.jetbrains.annotations.NotNull()
    private final okhttp3.OkHttpClient client = null;
    @org.jetbrains.annotations.Nullable()
    private okhttp3.WebSocket webSocket;
    @org.jetbrains.annotations.NotNull()
    private final com.liquiditylens.data.MarketHistoryBuffer historyBuffer = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.liquiditylens.data.MarketSnapshot> _marketData = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.liquiditylens.data.MarketSnapshot> marketData = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.liquiditylens.data.ConnectionState> _connectionState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.liquiditylens.data.ConnectionState> connectionState = null;
    
    public MarketDataWebSocket(@org.jetbrains.annotations.NotNull()
    java.lang.String serverUrl) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.liquiditylens.data.MarketSnapshot> getMarketData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.liquiditylens.data.ConnectionState> getConnectionState() {
        return null;
    }
    
    /**
     * Get Ring Buffer for replay/history features.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.liquiditylens.data.MarketHistoryBuffer getHistoryBuffer() {
        return null;
    }
    
    public final void connect() {
    }
    
    public final void disconnect() {
    }
    
    public MarketDataWebSocket() {
        super();
    }
}