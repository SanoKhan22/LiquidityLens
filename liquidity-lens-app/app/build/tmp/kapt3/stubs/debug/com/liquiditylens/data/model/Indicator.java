package com.liquiditylens.data.model;

/**
 * Indicator Types
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lcom/liquiditylens/data/model/Indicator;", "", "()V", "EMA", "MACD", "RSI", "SMA", "Lcom/liquiditylens/data/model/Indicator$EMA;", "Lcom/liquiditylens/data/model/Indicator$MACD;", "Lcom/liquiditylens/data/model/Indicator$RSI;", "Lcom/liquiditylens/data/model/Indicator$SMA;", "app_debug"})
public abstract class Indicator {
    
    private Indicator() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u0011\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H\u00c6\u0003J%\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0019\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lcom/liquiditylens/data/model/Indicator$EMA;", "Lcom/liquiditylens/data/model/Indicator;", "period", "", "data", "", "", "(ILjava/util/List;)V", "getData", "()Ljava/util/List;", "getPeriod", "()I", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_debug"})
    public static final class EMA extends com.liquiditylens.data.model.Indicator {
        private final int period = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.lang.Double> data = null;
        
        public EMA(int period, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.Double> data) {
        }
        
        public final int getPeriod() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Double> getData() {
            return null;
        }
        
        public final int component1() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Double> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.liquiditylens.data.model.Indicator.EMA copy(int period, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.Double> data) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u00a2\u0006\u0002\u0010\u0007J\u0011\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\u00c6\u0003J\u0011\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\u00c6\u0003J\u0011\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\u00c6\u0003J?\u0010\u000f\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0019\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0019\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t\u00a8\u0006\u0018"}, d2 = {"Lcom/liquiditylens/data/model/Indicator$MACD;", "Lcom/liquiditylens/data/model/Indicator;", "macdLine", "", "", "signalLine", "histogram", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getHistogram", "()Ljava/util/List;", "getMacdLine", "getSignalLine", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class MACD extends com.liquiditylens.data.model.Indicator {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.lang.Double> macdLine = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.lang.Double> signalLine = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.lang.Double> histogram = null;
        
        public MACD(@org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.Double> macdLine, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.Double> signalLine, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.Double> histogram) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Double> getMacdLine() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Double> getSignalLine() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Double> getHistogram() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Double> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Double> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Double> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.liquiditylens.data.model.Indicator.MACD copy(@org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.Double> macdLine, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.Double> signalLine, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.Double> histogram) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u0011\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H\u00c6\u0003J%\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0019\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lcom/liquiditylens/data/model/Indicator$RSI;", "Lcom/liquiditylens/data/model/Indicator;", "period", "", "data", "", "", "(ILjava/util/List;)V", "getData", "()Ljava/util/List;", "getPeriod", "()I", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_debug"})
    public static final class RSI extends com.liquiditylens.data.model.Indicator {
        private final int period = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.lang.Double> data = null;
        
        public RSI(int period, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.Double> data) {
        }
        
        public final int getPeriod() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Double> getData() {
            return null;
        }
        
        public final int component1() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Double> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.liquiditylens.data.model.Indicator.RSI copy(int period, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.Double> data) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u0011\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H\u00c6\u0003J%\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0019\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lcom/liquiditylens/data/model/Indicator$SMA;", "Lcom/liquiditylens/data/model/Indicator;", "period", "", "data", "", "", "(ILjava/util/List;)V", "getData", "()Ljava/util/List;", "getPeriod", "()I", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_debug"})
    public static final class SMA extends com.liquiditylens.data.model.Indicator {
        private final int period = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.lang.Double> data = null;
        
        public SMA(int period, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.Double> data) {
        }
        
        public final int getPeriod() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Double> getData() {
            return null;
        }
        
        public final int component1() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Double> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.liquiditylens.data.model.Indicator.SMA copy(int period, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.Double> data) {
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
}