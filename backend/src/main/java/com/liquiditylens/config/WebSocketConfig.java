package com.liquiditylens.config;

import com.liquiditylens.service.BinaryBroadcasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocket Configuration for Binary Market Data Broadcasting.
 * 
 * Endpoint: ws://localhost:8080/market-feed
 * Protocol: Binary (NOT Text/JSON)
 */
@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final BinaryBroadcasterService binaryBroadcasterService;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(binaryBroadcasterService, "/market-feed")
                .setAllowedOrigins("*"); // For development; restrict in production
    }
}
