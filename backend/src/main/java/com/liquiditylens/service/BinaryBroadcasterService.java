package com.liquiditylens.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Binary Broadcaster Service - Sends market data to connected mobile clients.
 * 
 * Protocol: Binary (ByteBuffer) - NOT JSON
 * Rationale: JSON parsing on mobile is CPU/battery intensive.
 *            Binary parsing is instant (no deserialization overhead).
 */
@Slf4j
@Service
public class BinaryBroadcasterService extends BinaryWebSocketHandler {

    // Thread-safe set of active client sessions
    private final Set<WebSocketSession> activeSessions = ConcurrentHashMap.newKeySet();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        activeSessions.add(session);
        log.info("Client connected. Total clients: {}", activeSessions.size());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        activeSessions.remove(session);
        log.info("Client disconnected. Total clients: {}", activeSessions.size());
    }

    /**
     * Broadcast binary market data to all connected clients.
     * 
     * @param binaryData ByteBuffer containing serialized market data (640 bytes)
     */
    public void broadcast(ByteBuffer binaryData) {
        if (activeSessions.isEmpty()) {
            return; // No clients connected, skip broadcast
        }

        BinaryMessage message = new BinaryMessage(binaryData);
        
        // Send to all connected clients
        activeSessions.removeIf(session -> {
            try {
                if (session.isOpen()) {
                    session.sendMessage(message);
                    return false; // Keep in set
                }
            } catch (IOException e) {
                log.error("Failed to send message to client: {}", session.getId(), e);
            }
            return true; // Remove from set
        });
    }

    public int getActiveClientCount() {
        return activeSessions.size();
    }
}
