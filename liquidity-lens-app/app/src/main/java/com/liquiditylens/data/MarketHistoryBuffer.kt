package com.liquiditylens.data

import android.util.Log

/**
 * Market History Buffer - The "Market DVR" Engine
 * 
 * Fixed-size Circular Buffer for storing time-series market data.
 * Capacity: 3000 snapshots (5 minutes @ 10 updates/sec)
 * Memory: ~2MB (3000 * 640 bytes per snapshot)
 * 
 * CRITICAL: Zero-Allocation Pattern
 * - Uses fixed-size Array (NOT ArrayList)
 * - Overwrites oldest data when full
 * - No object creation in add() method
 */
class MarketHistoryBuffer(
    private val capacity: Int = 3000
) {
    // Pre-allocated array - NEVER resized
    private val buffer: Array<MarketSnapshot?> = arrayOfNulls(capacity)
    
    // Write position (head pointer)
    private var head = 0
    
    // Current size (0 to capacity)
    private var size = 0
    
    /**
     * Add a market snapshot to the ring buffer.
     * 
     * Zero-GC Pattern: Overwrites existing slot instead of creating new array.
     * Thread-safe: Synchronized to prevent race conditions.
     */
    // Total writes counter (for absolute indexing)
    private var totalWrites: Long = 0
    
    /**
     * Add a market snapshot to the ring buffer.
     * 
     * Zero-GC Pattern: Overwrites existing slot instead of creating new array.
     * Thread-safe: Synchronized to prevent race conditions.
     */
    @Synchronized
    fun add(snapshot: MarketSnapshot) {
        buffer[head] = snapshot
        head = (head + 1) % capacity  // Circular indexing
        totalWrites++
        
        if (size < capacity) {
            size++
        }
        
        // Log every 100 snapshots for monitoring
        if (size % 100 == 0) {
            Log.d("RingBuffer", "Buffer size: $size / $capacity")
        }
    }
    
    /**
     * Get snapshot at specific offset from most recent.
     * 
     * @param offset 0 = most recent, 1 = one update ago, etc.
     * @return Snapshot or null if offset >= size
     */
    @Synchronized
    fun getSnapshot(offset: Int): MarketSnapshot? {
        if (offset < 0 || offset >= size) {
            return null
        }
        
        // Calculate actual index: go backward from head
        val index = (head - 1 - offset + capacity) % capacity
        return buffer[index]
    }

    /**
     * Get snapshot by absolute sequence ID.
     * Allows locking to a specific frame even as new data arrives.
     */
    @Synchronized
    fun getSnapshotByAbsoluteIndex(absoluteIndex: Long): MarketSnapshot? {
        if (absoluteIndex < 0) return null
        
        // Check if index is valid (not too old, not in future)
        if (absoluteIndex >= totalWrites) return null // Future
        if (absoluteIndex < totalWrites - size) return null // Overwritten
        
        // Calculate buffer index
        // absoluteIndex 0 -> buffer[0]
        // absoluteIndex = capacity -> buffer[0] (wrapped)
        val index = (absoluteIndex % capacity).toInt()
        return buffer[index]
    }
    
    /**
     * Get total number of writes (sequence ID).
     */
    @Synchronized
    fun getTotalWrites(): Long = totalWrites
    
    /**
     * Get range of snapshots for replay feature.
     * 
     * @param startOffset Offset from most recent (0 = now)
     * @param count Number of snapshots to retrieve
     * @return List of snapshots (may be less than count if not enough data)
     */
    @Synchronized
    fun getRange(startOffset: Int, count: Int): List<MarketSnapshot> {
        val result = mutableListOf<MarketSnapshot>()
        
        for (i in startOffset until minOf(startOffset + count, size)) {
            getSnapshot(i)?.let { result.add(it) }
        }
        
        return result
    }
    
    /**
     * Get all snapshots from last N seconds.
     * 
     * @param seconds Number of seconds to look back
     * @return List of snapshots from that time period
     */
    @Synchronized
    fun getLastNSeconds(seconds: Int): List<MarketSnapshot> {
        val result = mutableListOf<MarketSnapshot>()
        val cutoffTime = System.currentTimeMillis() - (seconds * 1000)
        
        // Iterate from most recent backward
        for (i in 0 until size) {
            val snapshot = getSnapshot(i) ?: break
            
            if (snapshot.timestamp >= cutoffTime) {
                result.add(snapshot)
            } else {
                break  // Older than cutoff, stop searching
            }
        }
        
        return result
    }
    
    /**
     * Get current size of buffer.
     */
    @Synchronized
    fun getCurrentSize(): Int = size
    
    /**
     * Get buffer capacity.
     */
    fun getCapacity(): Int = capacity
    
    /**
     * Check if buffer is full.
     */
    @Synchronized
    fun isFull(): Boolean = size >= capacity
    
    /**
     * Clear all data (for testing).
     */
    @Synchronized
    fun clear() {
        head = 0
        size = 0
        Log.d("RingBuffer", "Buffer cleared")
    }
}
