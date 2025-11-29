#!/bin/bash

# LiquidityLens Backend Startup Script
# This ensures the backend always starts with Java 21 and handles port conflicts

JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
JAVA=$JAVA_HOME/bin/java
JAR_PATH=backend/target/liquidity-lens-backend-1.0.0-SNAPSHOT.jar
LOG_FILE=backend.log
PORT=8080

echo "🚀 Starting LiquidityLens Backend..."

# Check if port is already in use
if lsof -Pi :$PORT -sTCP:LISTEN -t >/dev/null 2>&1; then
    echo "⚠️  Port $PORT is already in use. Stopping existing process..."
    lsof -ti:$PORT | xargs kill -9 2>/dev/null
    sleep 2
fi

# Start the backend
echo "📡 Starting backend on port $PORT..."
$JAVA -jar $JAR_PATH > $LOG_FILE 2>&1 &
BACKEND_PID=$!

# Wait for startup
sleep 3

# Check if backend is running
if ps -p $BACKEND_PID > /dev/null 2>&1; then
    echo "✅ Backend started successfully (PID: $BACKEND_PID)"
    echo "📋 Logs: tail -f $LOG_FILE"
    echo "🌐 WebSocket endpoint: ws://$(hostname -I | awk '{print $1}'):$PORT/market-feed"
else
    echo "❌ Backend failed to start. Check logs:"
    tail -20 $LOG_FILE
    exit 1
fi
