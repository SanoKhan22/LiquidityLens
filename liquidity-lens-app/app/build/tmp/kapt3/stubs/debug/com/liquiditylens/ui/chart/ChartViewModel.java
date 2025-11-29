package com.liquiditylens.ui.chart;

/**
 * ViewModel for Chart Screen
 *
 * Manages chart state, real-time WebSocket connection, and candle data.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000fJ\b\u0010\u0015\u001a\u00020\rH\u0014J\u0010\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/liquiditylens/ui/chart/ChartViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_chartState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/liquiditylens/data/model/ChartState;", "chartState", "Lkotlinx/coroutines/flow/StateFlow;", "getChartState", "()Lkotlinx/coroutines/flow/StateFlow;", "klineWebSocket", "Lcom/liquiditylens/data/websocket/BinanceKlineWebSocket;", "changeSymbol", "", "symbol", "", "changeTimeframe", "timeframe", "Lcom/liquiditylens/data/model/Timeframe;", "connect", "interval", "onCleared", "updateCandle", "newCandle", "Lcom/liquiditylens/data/model/Candle;", "app_debug"})
public final class ChartViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.liquiditylens.data.model.ChartState> _chartState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.liquiditylens.data.model.ChartState> chartState = null;
    @org.jetbrains.annotations.Nullable()
    private com.liquiditylens.data.websocket.BinanceKlineWebSocket klineWebSocket;
    
    public ChartViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.liquiditylens.data.model.ChartState> getChartState() {
        return null;
    }
    
    /**
     * Connect to WebSocket for a specific symbol and timeframe
     */
    public final void connect(@org.jetbrains.annotations.NotNull()
    java.lang.String symbol, @org.jetbrains.annotations.NotNull()
    java.lang.String interval) {
    }
    
    /**
     * Update or append candle to the list
     */
    private final void updateCandle(com.liquiditylens.data.model.Candle newCandle) {
    }
    
    /**
     * Change timeframe
     */
    public final void changeTimeframe(@org.jetbrains.annotations.NotNull()
    com.liquiditylens.data.model.Timeframe timeframe) {
    }
    
    /**
     * Change symbol
     */
    public final void changeSymbol(@org.jetbrains.annotations.NotNull()
    java.lang.String symbol) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}