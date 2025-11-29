package com.liquiditylens.data.model;

/**
 * Candle (OHLCV) Data Model
 *
 * Represents a single candlestick with OHLCV (Open, High, Low, Close, Volume) data.
 * Used for both real-time updates and historical data caching.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 12\u00020\u0001:\u00011B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0002\u0010\u0011J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0010H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0007H\u00c6\u0003J\t\u0010&\u001a\u00020\tH\u00c6\u0003J\t\u0010\'\u001a\u00020\tH\u00c6\u0003J\t\u0010(\u001a\u00020\tH\u00c6\u0003J\t\u0010)\u001a\u00020\tH\u00c6\u0003J\t\u0010*\u001a\u00020\tH\u00c6\u0003Jw\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u00c6\u0001J\u0013\u0010,\u001a\u00020\u00102\b\u0010-\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010.\u001a\u00020/H\u00d6\u0001J\t\u00100\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u000e\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0011\u0010\r\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013\u00a8\u00062"}, d2 = {"Lcom/liquiditylens/data/model/Candle;", "", "id", "", "symbol", "interval", "openTime", "", "open", "", "high", "low", "close", "volume", "closeTime", "isFinal", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JDDDDDJZ)V", "getClose", "()D", "getCloseTime", "()J", "getHigh", "getId", "()Ljava/lang/String;", "getInterval", "()Z", "getLow", "getOpen", "getOpenTime", "getSymbol", "getVolume", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "app_release"})
@androidx.room.Entity(tableName = "candles")
public final class Candle {
    @androidx.room.PrimaryKey()
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String symbol = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String interval = null;
    private final long openTime = 0L;
    private final double open = 0.0;
    private final double high = 0.0;
    private final double low = 0.0;
    private final double close = 0.0;
    private final double volume = 0.0;
    private final long closeTime = 0L;
    private final boolean isFinal = false;
    @org.jetbrains.annotations.NotNull()
    public static final com.liquiditylens.data.model.Candle.Companion Companion = null;
    
    public Candle(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String symbol, @org.jetbrains.annotations.NotNull()
    java.lang.String interval, long openTime, double open, double high, double low, double close, double volume, long closeTime, boolean isFinal) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
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
    
    public final long getOpenTime() {
        return 0L;
    }
    
    public final double getOpen() {
        return 0.0;
    }
    
    public final double getHigh() {
        return 0.0;
    }
    
    public final double getLow() {
        return 0.0;
    }
    
    public final double getClose() {
        return 0.0;
    }
    
    public final double getVolume() {
        return 0.0;
    }
    
    public final long getCloseTime() {
        return 0L;
    }
    
    public final boolean isFinal() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    public final long component10() {
        return 0L;
    }
    
    public final boolean component11() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    public final long component4() {
        return 0L;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    public final double component6() {
        return 0.0;
    }
    
    public final double component7() {
        return 0.0;
    }
    
    public final double component8() {
        return 0.0;
    }
    
    public final double component9() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.liquiditylens.data.model.Candle copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String symbol, @org.jetbrains.annotations.NotNull()
    java.lang.String interval, long openTime, double open, double high, double low, double close, double volume, long closeTime, boolean isFinal) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bJ\n\u0010\t\u001a\u00020\n*\u00020\u000bJ\n\u0010\f\u001a\u00020\r*\u00020\u000bJ\n\u0010\u000e\u001a\u00020\n*\u00020\u000b\u00a8\u0006\u000f"}, d2 = {"Lcom/liquiditylens/data/model/Candle$Companion;", "", "()V", "generateId", "", "symbol", "interval", "openTime", "", "bodySize", "", "Lcom/liquiditylens/data/model/Candle;", "isBullish", "", "range", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Generate unique ID for candle
         */
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String generateId(@org.jetbrains.annotations.NotNull()
        java.lang.String symbol, @org.jetbrains.annotations.NotNull()
        java.lang.String interval, long openTime) {
            return null;
        }
        
        /**
         * Check if candle is bullish (close > open)
         */
        public final boolean isBullish(@org.jetbrains.annotations.NotNull()
        com.liquiditylens.data.model.Candle $this$isBullish) {
            return false;
        }
        
        /**
         * Calculate candle body size
         */
        public final double bodySize(@org.jetbrains.annotations.NotNull()
        com.liquiditylens.data.model.Candle $this$bodySize) {
            return 0.0;
        }
        
        /**
         * Calculate full candle range
         */
        public final double range(@org.jetbrains.annotations.NotNull()
        com.liquiditylens.data.model.Candle $this$range) {
            return 0.0;
        }
    }
}