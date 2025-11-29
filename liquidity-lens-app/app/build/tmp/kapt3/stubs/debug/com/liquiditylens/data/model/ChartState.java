package com.liquiditylens.data.model;

/**
 * Chart State
 *
 * Represents the current state of the chart UI.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b(\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0091\u0001\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000e\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u0014J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0010\u0010\'\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\u0010\u0010(\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\t\u0010)\u001a\u00020\u000eH\u00c6\u0003J\t\u0010*\u001a\u00020\u0006H\u00c6\u0003J\t\u0010+\u001a\u00020\u0006H\u00c6\u0003J\u000f\u0010,\u001a\b\u0012\u0004\u0012\u00020\t0\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u000bH\u00c6\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u0010\u0010/\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\t\u00100\u001a\u00020\u000eH\u00c6\u0003J\t\u00101\u001a\u00020\u000eH\u00c6\u0003J\u009a\u0001\u00102\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0013\u001a\u00020\u000eH\u00c6\u0001\u00a2\u0006\u0002\u00103J\u0013\u00104\u001a\u00020\u000b2\b\u00105\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00106\u001a\u000207H\u00d6\u0001J\t\u00108\u001a\u00020\u0006H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u001eR\u0015\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001f\u0010\u001aR\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b \u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0010\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\"R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0011\u0010\u0013\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\"\u00a8\u00069"}, d2 = {"Lcom/liquiditylens/data/model/ChartState;", "", "candles", "", "Lcom/liquiditylens/data/model/Candle;", "symbol", "", "interval", "indicators", "Lcom/liquiditylens/data/model/Indicator;", "isLoading", "", "error", "lastPrice", "", "priceChange24h", "priceChangePercent24h", "high24h", "low24h", "volume24h", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ZLjava/lang/String;Ljava/lang/Double;DDLjava/lang/Double;Ljava/lang/Double;D)V", "getCandles", "()Ljava/util/List;", "getError", "()Ljava/lang/String;", "getHigh24h", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getIndicators", "getInterval", "()Z", "getLastPrice", "getLow24h", "getPriceChange24h", "()D", "getPriceChangePercent24h", "getSymbol", "getVolume24h", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ZLjava/lang/String;Ljava/lang/Double;DDLjava/lang/Double;Ljava/lang/Double;D)Lcom/liquiditylens/data/model/ChartState;", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class ChartState {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.liquiditylens.data.model.Candle> candles = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String symbol = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String interval = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.liquiditylens.data.model.Indicator> indicators = null;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double lastPrice = null;
    private final double priceChange24h = 0.0;
    private final double priceChangePercent24h = 0.0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double high24h = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double low24h = null;
    private final double volume24h = 0.0;
    
    public ChartState(@org.jetbrains.annotations.NotNull()
    java.util.List<com.liquiditylens.data.model.Candle> candles, @org.jetbrains.annotations.NotNull()
    java.lang.String symbol, @org.jetbrains.annotations.NotNull()
    java.lang.String interval, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.liquiditylens.data.model.Indicator> indicators, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error, @org.jetbrains.annotations.Nullable()
    java.lang.Double lastPrice, double priceChange24h, double priceChangePercent24h, @org.jetbrains.annotations.Nullable()
    java.lang.Double high24h, @org.jetbrains.annotations.Nullable()
    java.lang.Double low24h, double volume24h) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.liquiditylens.data.model.Candle> getCandles() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSymbol() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getInterval() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.liquiditylens.data.model.Indicator> getIndicators() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getLastPrice() {
        return null;
    }
    
    public final double getPriceChange24h() {
        return 0.0;
    }
    
    public final double getPriceChangePercent24h() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getHigh24h() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getLow24h() {
        return null;
    }
    
    public final double getVolume24h() {
        return 0.0;
    }
    
    public ChartState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.liquiditylens.data.model.Candle> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component11() {
        return null;
    }
    
    public final double component12() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.liquiditylens.data.model.Indicator> component4() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component7() {
        return null;
    }
    
    public final double component8() {
        return 0.0;
    }
    
    public final double component9() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.liquiditylens.data.model.ChartState copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.liquiditylens.data.model.Candle> candles, @org.jetbrains.annotations.NotNull()
    java.lang.String symbol, @org.jetbrains.annotations.NotNull()
    java.lang.String interval, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.liquiditylens.data.model.Indicator> indicators, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error, @org.jetbrains.annotations.Nullable()
    java.lang.Double lastPrice, double priceChange24h, double priceChangePercent24h, @org.jetbrains.annotations.Nullable()
    java.lang.Double high24h, @org.jetbrains.annotations.Nullable()
    java.lang.Double low24h, double volume24h) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}