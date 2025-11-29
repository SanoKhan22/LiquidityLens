package com.liquiditylens.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Binance WebSocket Connector using Java 21 Virtual Threads.
 * 
 * Architecture:
 * - Uses java.net.http.WebSocket (NOT Spring STOMP)
 * - Runs on Virtual Thread Executor (high-throughput, low-overhead)
 * - Forwards data to MarketDataBuffer (Zero-GC pattern)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BinanceConnectorService {

    @Value("${binance.websocket.url}")
    private String binanceWebSocketUrl;

    private final MarketDataBuffer marketDataBuffer;
    private final BinaryBroadcasterService binaryBroadcaster;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private WebSocket webSocket;
    private final StringBuilder messageBuffer = new StringBuilder();
    
    // Virtual Thread Executor for WebSocket operations
    private final ScheduledExecutorService virtualThreadExecutor =
            Executors.newScheduledThreadPool(1, Thread.ofVirtual().factory());

    @PostConstruct
    public void connect() {
        log.info("Connecting to Binance WebSocket: {}", binanceWebSocketUrl);
        
        HttpClient client = HttpClient.newBuilder()
                .executor(virtualThreadExecutor)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        CompletableFuture<WebSocket> wsFuture = client.newWebSocketBuilder()
                .buildAsync(URI.create(binanceWebSocketUrl), new BinanceWebSocketListener());

        wsFuture.thenAccept(ws -> {
            this.webSocket = ws;
            log.info("Successfully connected to Binance WebSocket");
        }).exceptionally(ex -> {
            log.error("Failed to connect to Binance WebSocket", ex);
            // Retry after 5 seconds
            virtualThreadExecutor.schedule(this::connect, 5, TimeUnit.SECONDS);
            return null;
        });
    }

    @PreDestroy
    public void disconnect() {
        if (webSocket != null) {
            webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "Shutting down");
            log.info("Disconnected from Binance WebSocket");
        }
        virtualThreadExecutor.shutdown();
    }

    /**
     * WebSocket Listener implementation.
     * 
     * CRITICAL: The onText() method is the "hot path" - it receives 10 messages/second.
     * We must minimize allocations here.
     */
    private class BinanceWebSocketListener implements WebSocket.Listener {

        @Override
        public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
            // Accumulate message parts (Binance may send fragmented messages)
            messageBuffer.append(data);

            if (last) {
                try {
                    // Parse JSON to extract market data
                    String message = messageBuffer.toString();
                    messageBuffer.setLength(0); // Clear buffer for next message

                    JsonNode root = objectMapper.readTree(message);
                    
                    // Update the Zero-GC buffer (OVERWRITES existing arrays)
                    marketDataBuffer.update(root);
                    
                    // Broadcast binary data to connected clients
                    binaryBroadcaster.broadcast(marketDataBuffer.serializeToBinary());
                    
                } catch (Exception e) {
                    log.error("Error processing WebSocket message", e);
                }
            }

            // Request next message
            webSocket.request(1);
            return CompletableFuture.completedFuture(null);
        }

        @Override
        public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
            log.warn("WebSocket closed. Status: {}, Reason: {}", statusCode, reason);
            
            // Reconnect after delay
            virtualThreadExecutor.schedule(() -> connect(), 3, TimeUnit.SECONDS);
            
            return CompletableFuture.completedFuture(null);
        }

        @Override
        public void onError(WebSocket webSocket, Throwable error) {
            log.error("WebSocket error occurred", error);
        }
    }
}
