package com.liquiditylens.data.db;

/**
 * Data Access Object for Candle operations
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J&\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ.\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0011J.\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u00132\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\'J4\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u0017J \u0010\u0018\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0019J\u001c\u0010\u001a\u001a\u00020\u00032\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u00a7@\u00a2\u0006\u0002\u0010\u001c\u00a8\u0006\u001d"}, d2 = {"Lcom/liquiditylens/data/db/CandleDao;", "", "clearAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOldCandles", "symbol", "", "interval", "beforeTime", "", "(Ljava/lang/String;Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCandles", "", "Lcom/liquiditylens/data/model/Candle;", "limit", "", "(Ljava/lang/String;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCandlesFlow", "Lkotlinx/coroutines/flow/Flow;", "getCandlesInRange", "startTime", "endTime", "(Ljava/lang/String;Ljava/lang/String;JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLatestCandle", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertCandles", "candles", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
@androidx.room.Dao()
public abstract interface CandleDao {
    
    /**
     * Insert or update candles (upsert)
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertCandles(@org.jetbrains.annotations.NotNull()
    java.util.List<com.liquiditylens.data.model.Candle> candles, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Get candles for a specific symbol and interval
     */
    @androidx.room.Query(value = "\n        SELECT * FROM candles \n        WHERE symbol = :symbol AND interval = :interval \n        ORDER BY openTime ASC\n        LIMIT :limit\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCandles(@org.jetbrains.annotations.NotNull()
    java.lang.String symbol, @org.jetbrains.annotations.NotNull()
    java.lang.String interval, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.liquiditylens.data.model.Candle>> $completion);
    
    /**
     * Get candles as Flow for reactive updates
     */
    @androidx.room.Query(value = "\n        SELECT * FROM candles \n        WHERE symbol = :symbol AND interval = :interval \n        ORDER BY openTime ASC\n        LIMIT :limit\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.liquiditylens.data.model.Candle>> getCandlesFlow(@org.jetbrains.annotations.NotNull()
    java.lang.String symbol, @org.jetbrains.annotations.NotNull()
    java.lang.String interval, int limit);
    
    /**
     * Get candles within a time range
     */
    @androidx.room.Query(value = "\n        SELECT * FROM candles \n        WHERE symbol = :symbol \n        AND interval = :interval \n        AND openTime >= :startTime \n        AND openTime <= :endTime\n        ORDER BY openTime ASC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCandlesInRange(@org.jetbrains.annotations.NotNull()
    java.lang.String symbol, @org.jetbrains.annotations.NotNull()
    java.lang.String interval, long startTime, long endTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.liquiditylens.data.model.Candle>> $completion);
    
    /**
     * Get the latest candle
     */
    @androidx.room.Query(value = "\n        SELECT * FROM candles \n        WHERE symbol = :symbol AND interval = :interval \n        ORDER BY openTime DESC \n        LIMIT 1\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestCandle(@org.jetbrains.annotations.NotNull()
    java.lang.String symbol, @org.jetbrains.annotations.NotNull()
    java.lang.String interval, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.liquiditylens.data.model.Candle> $completion);
    
    /**
     * Delete old candles (keep only recent ones for storage optimization)
     */
    @androidx.room.Query(value = "\n        DELETE FROM candles \n        WHERE symbol = :symbol \n        AND interval = :interval \n        AND openTime < :beforeTime\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOldCandles(@org.jetbrains.annotations.NotNull()
    java.lang.String symbol, @org.jetbrains.annotations.NotNull()
    java.lang.String interval, long beforeTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Clear all candles
     */
    @androidx.room.Query(value = "DELETE FROM candles")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Data Access Object for Candle operations
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}