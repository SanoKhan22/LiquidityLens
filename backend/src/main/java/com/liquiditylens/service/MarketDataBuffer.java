package com.liquiditylens.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * CRITICAL: Zero-Allocation Market Data Buffer
 * 
 * This class pre-allocates all arrays and NEVER creates new objects
 * in the hot path to avoid Garbage Collection pauses.
 * 
 * Architecture Rule: NO "new" keyword inside update() method!
 */
@Slf4j
@Service
@Getter
public class MarketDataBuffer {

    // Pre-allocated arrays (Size: 20 for @depth20)
    // CRITICAL: These arrays are NEVER resized or replaced
    private final double[] bidPrices = new double[20];
    private final double[] bidVolumes = new double[20];
    private final double[] askPrices = new double[20];
    private final double[] askVolumes = new double[20];
    
    // Thread safety for concurrent reads/writes
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    
    // Timestamp of last update (for monitoring staleness)
    private volatile long lastUpdateTimestamp = 0;
    
    /**
     * Update market data from Binance JSON payload.
     * 
     * ZERO-GC Pattern: This method OVERWRITES existing array values
     * instead of creating new objects. This ensures NO garbage collection
     * pressure even at 10 updates/second.
     * 
     * @param depthUpdate JSON payload from Binance WebSocket
     */
    public void update(JsonNode depthUpdate) {
        lock.writeLock().lock();
        try {
            // Parse bids (sorted by price descending)
            JsonNode bids = depthUpdate.get("bids");
            for (int i = 0; i < Math.min(20, bids.size()); i++) {
                JsonNode bid = bids.get(i);
                bidPrices[i] = bid.get(0).asDouble();   // Price
                bidVolumes[i] = bid.get(1).asDouble();  // Volume
            }
            
            // Parse asks (sorted by price ascending)
            JsonNode asks = depthUpdate.get("asks");
            for (int i = 0; i < Math.min(20, asks.size()); i++) {
                JsonNode ask = asks.get(i);
                askPrices[i] = ask.get(0).asDouble();   // Price
                askVolumes[i] = ask.get(1).asDouble();  // Volume
            }
            
            lastUpdateTimestamp = System.currentTimeMillis();
            
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    /**
     * Serialize market data to binary format for mobile clients.
     * 
     * Binary Protocol:
     * - 20 bid prices (8 bytes each = 160 bytes)
     * - 20 bid volumes (8 bytes each = 160 bytes)
     * - 20 ask prices (8 bytes each = 160 bytes)
     * - 20 ask volumes (8 bytes each = 160 bytes)
     * Total: 640 bytes per frame
     * 
     * @return ByteBuffer containing binary market data
     */
    public ByteBuffer serializeToBinary() {
        lock.readLock().lock();
        try {
            ByteBuffer buffer = ByteBuffer.allocate(640);
            
            // Write bids
            for (double price : bidPrices) buffer.putDouble(price);
            for (double volume : bidVolumes) buffer.putDouble(volume);
            
            // Write asks
            for (double price : askPrices) buffer.putDouble(price);
            for (double volume : askVolumes) buffer.putDouble(volume);
            
            buffer.flip();
            return buffer;
            
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Check if market data is stale (no updates for >1 second).
     * Used for connection health monitoring.
     */
    public boolean isStale() {
        return (System.currentTimeMillis() - lastUpdateTimestamp) > 1000;
    }
}
