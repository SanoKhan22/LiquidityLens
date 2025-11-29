

# **LiquidityLens: High-Frequency Market Forensics Platform**

## **Master Engineering Plan & Software Design Document (SDD)**

**Version:** 1.0  
**Date:** May 2025  
**Author:** Future Senior Software Engineer  
**Classification:** Portfolio / Institutional Grade  
---

## **1\. Executive Summary**

### **1.1 Project Mission**

To democratize institutional-grade market forensics for retail traders. Most mobile trading applications (Binance, Robinhood) provide only a "snapshot" of the current price. **LiquidityLens** provides the "video"—a historical, replayable view of the Order Book (Level 2 Data) to analyze market microstructure events like "Flash Crashes," "Buy Walls," and liquidity vacuums.

### **1.2 The Unique Value Proposition (UVP)**

The application features a **"Market DVR" (Digital Video Recorder)**. Unlike competitors that discard data the moment it is rendered, LiquidityLens buffers the last 5 minutes of high-frequency data in a high-performance **Ring Buffer** within the device's RAM. This allows users to "scrub" backward in time to inspect exactly how liquidity shifted milliseconds before a major price move.  
---

## **2\. Technology Stack (Strict "Zero-Budget" Constraints)**

This stack is selected to maximize performance (throughput/latency) while strictly adhering to a $0.00 budget.

| Component | Technology | Justification & Constraints |
| :---- | :---- | :---- |
| **Data Source** | **Binance WebSocket API** | Provides free Level 2 Market Data (@depth20@100ms). **Constraint:** Single IP limit requires a proxy backend. |
| **Backend** | **Java 21 (Spring Boot 3.3+)** | **Virtual Threads (Project Loom)** provide high-throughput concurrency without the code complexity of Reactive (WebFlux). |
| **Cloud Host** | **Oracle Cloud Infrastructure (OCI)** | **Always Free Tier (ARM Ampere A1)**: 4 OCPUs, 24 GB RAM. Superior to AWS/Google free tiers. |
| **Mobile** | **Kotlin 2.0 \+ Jetpack Compose** | Kotlin 2.0 (K2 Compiler) for build speed. Jetpack Compose for declarative UI. |
| **Rendering** | **Compose Canvas API** | **CRITICAL:** Standard LazyColumn is too slow for 10Hz updates. We must draw pixels directly to the GPU. |
| **Networking** | **OkHttp 4 \+ Scarlet** | Scarlet (by Tinder) manages the WebSocket state machine (Connect \-\> Retry \-\> Backoff) automatically. |
| **Protocol** | **Binary (ByteBuffer)** | **Optimization:** The backend parses JSON and forwards **Binary** data to mobile to save phone CPU/Battery. |

---

## **3\. System Architecture**

### **3.1 Data Flow Diagram**

1. **Binance Exchange** emits JSON payload (10x/sec) $\\rightarrow$  
2. **Spring Boot Proxy** (Oracle Cloud) intercepts payload $\\rightarrow$  
3. **Ingestion Service** parses JSON into primitive arrays (Zero-Allocation) $\\rightarrow$  
4. **Broadcaster Service** serializes to Binary and pushes to all connected clients $\\rightarrow$  
5. **Android Client** receives Binary $\\rightarrow$ writes to **Ring Buffer** $\\rightarrow$  
6. **Canvas Renderer** reads from Ring Buffer $\\rightarrow$ Draws Frame.

---

## **4\. Detailed Implementation Phase (SDLC)**

### **Phase 1: Backend Engineering (The Proxy)**

**Objective:** Create a high-performance relay that protects your IP from Binance rate limits.

* **Step 1.1: Project Initialization**  
  * Use **Spring Initializr**. Dependencies: WebSocket, Lombok.  
  * **Critical Config:** Enable Virtual Threads in application.properties: spring.threads.virtual.enabled=true.  
* **Step 1.2: The Binance Connector**  
  * Implement StandardWebSocketClient to connect to wss://stream.binance.com:9443/ws/btcusdt@depth20@100ms.  
* **Step 1.3: Memory Optimization (The "Zero-GC" Pattern)**  
  * **Mistake to Avoid:** Do not create new Order() objects for every update.  
  * **Solution:** Create two pre-allocated arrays: double bidPrices and double bidVols (Size 20).  
  * **Logic:** When a JSON message arrives, parse it and *overwrite* the values in these existing arrays. This generates **Zero Garbage** for the Java Garbage Collector.  
* **Step 1.4: Binary Broadcasting**  
  * Convert the arrays into a ByteBuffer (8 bytes per double \* 40 values \= 320 bytes per frame).  
  * Send this lightweight byte array to connected mobile clients.

### **Phase 2: Android Core & Data Layer**

**Objective:** Efficiently ingest high-speed data without heating up the phone.

* **Step 2.1: The Circular Buffer (The DVR Engine)**  
  * Create a class MarketHistoryBuffer.  
  * **Storage:** private val history \= Array(3000) { MarketSnapshot() }.  
  * **Pointer:** private var headIndex \= 0\.  
  * **Write Logic:** history\[headIndex % 3000\].update(newData); headIndex++.  
  * **Why:** This creates a "rolling window" of 5 minutes (3000 frames @ 100ms). It never grows, never leaks memory.  
* **Step 2.2: The Repository**  
  * Use **Scarlet** to connect to your Spring Boot Proxy.  
  * On message receipt: Read bytes $\\rightarrow$ Write to Buffer $\\rightarrow$ Emit StateFlow event.

### **Phase 3: The Visualization Engine (Canvas)**

**Objective:** Render 60fps smooth graphics.

* **Step 3.1: The Canvas Composable**  
  * **Do Not Use:** Row, Column, or LazyColumn for the order book bars.  
  * **Use:** Canvas(modifier \= Modifier.fillMaxSize()) {... }.  
* **Step 3.2: The "Draw Loop"**  
  * Inside the Canvas onDraw:  
  * Loop through the *current* snapshot (from the Ring Buffer).  
  * Calculate X/Y coordinates: x \= width \* (volume / maxVolume).  
  * Call drawRect(color \= BidGreen,...) directly.  
* **Step 3.3: The "Heatmap" Logic**  
  * The background of the canvas should be a grid of colored pixels representing historical density.  
  * **Optimization:** Use an Android Bitmap for the heatmap. Shift the bitmap 1 pixel to the left every frame (canvas.drawBitmap) and draw the new line of data on the right edge. This is O(1) cost vs O(N) for redrawing everything.

### **Phase 4: UI/UX Polish ("Institutional Cyberpunk")**

**Objective:** Look like professional financial software.

* **Palette:**  
  * **Background:** \#0D1117 (Rich Dark Grey \- reduces OLED smearing).  
  * **Bids:** \#00E676 (Neon Green).  
  * **Asks:** \#FF1744 (Neon Red).  
  * **Text:** \#E6EDF3 (Off-white Mono-spaced).  
* **Typography:**  
  * **Font:** JetBrains Mono or Roboto Mono.  
  * **Reasoning:** Numbers in an order book must align vertically. Variable-width fonts cause the UI to "jitter" as numbers change.

---

## **5\. Technical Pitfalls & "Senior Engineer" Solutions**

| Common Mistake (Junior) | "Senior" Solution | Why? |
| :---- | :---- | :---- |
| **Using Double for money** | Use BigDecimal (Java) or Long (Micros) | Floating point math errors (0.1 \+ 0.2\!= 0.3). |
| **Using LazyColumn** | Use **Canvas API** | LazyColumn cannot handle 10 updates/sec without lagging the UI thread. |
| **Parsing JSON on Phone** | **Binary Protocol** | JSON parsing is CPU intensive. Binary parsing is instant and saves battery. |
| **Direct Exchange Connection** | **Proxy Middleware** | 100 users connecting to Binance from 1 IP \= Instant Ban. Proxy fans-out 1 connection to N users. |
| **Allocating Objects in Loop** | **Object Pooling / Ring Buffer** | Prevents "Stop-the-World" Garbage Collection pauses (Jank). |

---

## **6\. AI Agent Prompting Strategy**

When using AI (Claude/ChatGPT) to help write code, use these specific prompts to ensure high-quality output.  
**Prompt for Backend:**  
"Act as a Senior Java Low-Latency Engineer. Write a Spring Boot 3 service using Java 21 Virtual Threads. It must connect to a WebSocket, parse JSON updates into a pre-allocated primitive double array (to avoid Garbage Collection), and broadcast this array as a binary ByteBuffer to clients. Do not use reactive streams; use imperative blocking code on virtual threads."  
**Prompt for Android Canvas:**  
"Act as a Senior Android Graphics Engineer. Write a Jetpack Compose custom Composable that draws a financial Order Book using the Canvas API. It should take a MarketSnapshot object. Use drawRect for the volume bars. Ensure all Paint and Path objects are allocated outside the draw phase to prevent memory churn. Optimize for 60fps."  
**Prompt for Ring Buffer:**  
"Write a thread-safe, fixed-size Circular Buffer class in Kotlin for storing OrderBook snapshots. Use an array-based implementation. Include a getSnapshot(offset: Int) method to allow retrieving historical data for a 'replay' feature."  
---

## **7\. Final Deliverable Checklist**

1. **Source Code:** Hosted on GitHub (Clean commit history).  
2. **Docker Image:** Hosted on GitHub Container Registry.  
3. **Live App:** .apk file available in Releases.  
4. **Documentation:** README.md containing:  
   * Architecture Diagram.  
   * "Performance Optimization" section explaining the Ring Buffer and Binary Protocol.  
   * Demo GIF showing the "Scrub/Replay" feature.

This document serves as the "Constitution" for your project. Deviating from the **Zero-Allocation** or **Canvas-Rendering** principles will downgrade the project from "Institutional" to "Retail." Stick to the plan.