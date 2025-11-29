package com.liquiditylens.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Candle (OHLCV) Data Model
 * 
 * Represents a single candlestick with OHLCV (Open, High, Low, Close, Volume) data.
 * Used for both real-time updates and historical data caching.
 */
@Entity(tableName = "candles")
data class Candle(
    @PrimaryKey 
    val id: String,              // Format: symbol_interval_timestamp (e.g. "BTCUSDT_1m_1672515780000")
    val symbol: String,          // Trading pair (e.g. "BTCUSDT")
    val interval: String,        // Timeframe (1m, 5m, 15m, 1h, 4h, 1d, 1w)
    val openTime: Long,          // Candle open time (milliseconds)
    val open: Double,            // Open price
    val high: Double,            // High price
    val low: Double,             // Low price
    val close: Double,           // Close price
    val volume: Double,          // Trading volume
    val closeTime: Long,         // Candle close time (milliseconds)
    val isFinal: Boolean = false // true if candle is closed/finalized
) {
    companion object {
        /**
         * Generate unique ID for candle
         */
        fun generateId(symbol: String, interval: String, openTime: Long): String {
            return "${symbol}_${interval}_${openTime}"
        }
        
        /**
         * Check if candle is bullish (close > open)
         */
        fun Candle.isBullish(): Boolean = close >= open
        
        /**
         * Calculate candle body size
         */
        fun Candle.bodySize(): Double = kotlin.math.abs(close - open)
        
        /**
         * Calculate full candle range
         */
        fun Candle.range(): Double = high - low
    }
}

/**
 * Chart State
 * 
 * Represents the current state of the chart UI.
 */
data class ChartState(
    val candles: List<Candle> = emptyList(),
    val symbol: String = "BTCUSDT",
    val interval: String = "1m",
    val indicators: List<Indicator> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val lastPrice: Double? = null,
    val priceChange24h: Double = 0.0,
    val priceChangePercent24h: Double = 0.0,
    val high24h: Double? = null,
    val low24h: Double? = null,
    val volume24h: Double = 0.0
)

/**
 * Indicator Types
 */
sealed class Indicator {
    data class SMA(val period: Int, val data: List<Double?>) : Indicator()
    data class EMA(val period: Int, val data: List<Double?>) : Indicator()
    data class RSI(val period: Int, val data: List<Double?>) : Indicator()
    data class MACD(
        val macdLine: List<Double?>,
        val signalLine: List<Double?>,
        val histogram: List<Double?>
    ) : Indicator()
}

/**
 * Timeframe options
 */
enum class Timeframe(val value: String, val displayName: String) {
    ONE_MIN("1m", "1m"),
    FIVE_MIN("5m", "5m"),
    FIFTEEN_MIN("15m", "15m"),
    THIRTY_MIN("30m", "30m"),
    ONE_HOUR("1h", "1h"),
    FOUR_HOUR("4h", "4h"),
    ONE_DAY("1d", "1d"),
    ONE_WEEK("1w", "1w");
    
    companion object {
        fun fromValue(value: String): Timeframe {
            return values().find { it.value == value } ?: ONE_MIN
        }
    }
}
