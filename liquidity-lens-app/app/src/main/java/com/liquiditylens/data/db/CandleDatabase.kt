package com.liquiditylens.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.liquiditylens.data.model.Candle

/**
 * Room Database for LiquidityLens
 */
@Database(
    entities = [Candle::class],
    version = 1,
    exportSchema = false
)
abstract class CandleDatabase : RoomDatabase() {
    
    abstract fun candleDao(): CandleDao
    
    companion object {
        @Volatile
        private var INSTANCE: CandleDatabase? = null
        
        fun getDatabase(context: Context): CandleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CandleDatabase::class.java,
                    "liquidity_lens_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
