package com.liquiditylens.data.websocket;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u000eH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0006H\u00c6\u0003J\t\u0010 \u001a\u00020\u0006H\u00c6\u0003J\t\u0010!\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0006H\u00c6\u0003J\t\u0010#\u001a\u00020\u0006H\u00c6\u0003J\t\u0010$\u001a\u00020\u0006H\u00c6\u0003J\t\u0010%\u001a\u00020\u0006H\u00c6\u0003Jm\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u000eH\u00c6\u0001J\u0013\u0010\'\u001a\u00020\u000e2\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010)\u001a\u00020*H\u00d6\u0001J\t\u0010+\u001a\u00020\u0006H\u00d6\u0001R\u0016\u0010\t\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\n\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0016\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0016\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0016R\u0016\u0010\u000b\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0016\u0010\b\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0016\u0010\f\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011\u00a8\u0006,"}, d2 = {"Lcom/liquiditylens/data/websocket/KlineData;", "", "openTime", "", "closeTime", "symbol", "", "interval", "open", "close", "high", "low", "volume", "isFinal", "", "(JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getClose", "()Ljava/lang/String;", "getCloseTime", "()J", "getHigh", "getInterval", "()Z", "getLow", "getOpen", "getOpenTime", "getSymbol", "getVolume", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
final class KlineData {
    @com.google.gson.annotations.SerializedName(value = "t")
    private final long openTime = 0L;
    @com.google.gson.annotations.SerializedName(value = "T")
    private final long closeTime = 0L;
    @com.google.gson.annotations.SerializedName(value = "s")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String symbol = null;
    @com.google.gson.annotations.SerializedName(value = "i")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String interval = null;
    @com.google.gson.annotations.SerializedName(value = "o")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String open = null;
    @com.google.gson.annotations.SerializedName(value = "c")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String close = null;
    @com.google.gson.annotations.SerializedName(value = "h")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String high = null;
    @com.google.gson.annotations.SerializedName(value = "l")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String low = null;
    @com.google.gson.annotations.SerializedName(value = "v")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String volume = null;
    @com.google.gson.annotations.SerializedName(value = "x")
    private final boolean isFinal = false;
    
    public KlineData(long openTime, long closeTime, @org.jetbrains.annotations.NotNull()
    java.lang.String symbol, @org.jetbrains.annotations.NotNull()
    java.lang.String interval, @org.jetbrains.annotations.NotNull()
    java.lang.String open, @org.jetbrains.annotations.NotNull()
    java.lang.String close, @org.jetbrains.annotations.NotNull()
    java.lang.String high, @org.jetbrains.annotations.NotNull()
    java.lang.String low, @org.jetbrains.annotations.NotNull()
    java.lang.String volume, boolean isFinal) {
        super();
    }
    
    public final long getOpenTime() {
        return 0L;
    }
    
    public final long getCloseTime() {
        return 0L;
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
    public final java.lang.String getOpen() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getClose() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHigh() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLow() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVolume() {
        return null;
    }
    
    public final boolean isFinal() {
        return false;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final boolean component10() {
        return false;
    }
    
    public final long component2() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.liquiditylens.data.websocket.KlineData copy(long openTime, long closeTime, @org.jetbrains.annotations.NotNull()
    java.lang.String symbol, @org.jetbrains.annotations.NotNull()
    java.lang.String interval, @org.jetbrains.annotations.NotNull()
    java.lang.String open, @org.jetbrains.annotations.NotNull()
    java.lang.String close, @org.jetbrains.annotations.NotNull()
    java.lang.String high, @org.jetbrains.annotations.NotNull()
    java.lang.String low, @org.jetbrains.annotations.NotNull()
    java.lang.String volume, boolean isFinal) {
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