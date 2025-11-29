package com.liquiditylens.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.liquiditylens.data.model.Candle
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Candle operations
 */
@Dao
interface CandleDao {
    
    /**
     * Insert or update candles (upsert)
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCandles(candles: List<Candle>)
    
    /**
     * Get candles for a specific symbol and interval
     */
    @Query("""
        SELECT * FROM candles 
        WHERE symbol = :symbol AND interval = :interval 
        ORDER BY openTime ASC
        LIMIT :limit
    """)
    suspend fun getCandles(symbol: String, interval: String, limit: Int = 500): List<Candle>
    
    /**
     * Get candles as Flow for reactive updates
     */
    @Query("""
        SELECT * FROM candles 
        WHERE symbol = :symbol AND interval = :interval 
        ORDER BY openTime ASC
        LIMIT :limit
    """)
    fun getCandlesFlow(symbol: String, interval: String, limit: Int = 500): Flow<List<Candle>>
    
    /**
     * Get candles within a time range
     */
    @Query("""
        SELECT * FROM candles 
        WHERE symbol = :symbol 
        AND interval = :interval 
        AND openTime >= :startTime 
        AND openTime <= :endTime
        ORDER BY openTime ASC
    """)
    suspend fun getCandlesInRange(
        symbol: String,
        interval: String,
        startTime: Long,
        endTime: Long
    ): List<Candle>
    
    /**
     * Get the latest candle
     */
    @Query("""
        SELECT * FROM candles 
        WHERE symbol = :symbol AND interval = :interval 
        ORDER BY openTime DESC 
        LIMIT 1
    """)
    suspend fun getLatestCandle(symbol: String, interval: String): Candle?
    
    /**
     * Delete old candles (keep only recent ones for storage optimization)
     */
    @Query("""
        DELETE FROM candles 
        WHERE symbol = :symbol 
        AND interval = :interval 
        AND openTime < :beforeTime
    """)
    suspend fun deleteOldCandles(symbol: String, interval: String, beforeTime: Long)
    
    /**
     * Clear all candles
     */
    @Query("DELETE FROM candles")
    suspend fun clearAll()
}
