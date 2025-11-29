package com.liquiditylens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * LiquidityLens Backend - High-Frequency Market Data Proxy
 * 
 * Architecture: Zero-Allocation + Virtual Threads
 * - Uses pre-allocated arrays for market data (NO GC pressure)
 * - Uses Java 21 Virtual Threads for high-throughput concurrency
 * - Broadcasts binary data to mobile clients (saves mobile CPU/battery)
 */
@SpringBootApplication
public class LiquidityLensApplication {

    public static void main(String[] args) {
        // Verify Java 21 Virtual Threads are available
        if (Runtime.version().feature() < 21) {
            throw new IllegalStateException("Java 21+ required for Virtual Threads");
        }
        
        SpringApplication.run(LiquidityLensApplication.class, args);
    }
}
