#!/bin/bash

# LiquidityLens Backend Shutdown Script

PORT=8080

echo "🛑 Stopping LiquidityLens Backend..."

# Find and kill process on port 8080
if lsof -Pi :$PORT -sTCP:LISTEN -t >/dev/null 2>&1; then
    PID=$(lsof -ti:$PORT)
    kill -9 $PID 2>/dev/null
    echo "✅ Stopped backend (PID: $PID)"
else
    echo "ℹ️  No backend process found on port $PORT"
fi
