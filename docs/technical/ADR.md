# Architecture Decision Records (ADR)

> **Purpose**: Log all significant technical decisions with clear rationale. This proves engineering maturity to investors and future team members.

---

## ADR-001: Use of Ring Buffer for Market Data

**Status**: ✅ Accepted  
**Date**: 2025-11-23  
**Deciders**: Core Team

### Context

We need to store 5 minutes of high-frequency market data (10Hz = 3000 snapshots) on mobile devices with limited RAM. The data must be:
- Instantly accessible for "Market DVR" replay functionality
- Memory-bounded (no unbounded growth)
- Zero-allocation during live updates (60fps UI requirement)

### Decision

Use a **fixed-size primitive array (Ring Buffer)** with circular indexing instead of:
- SQLite database (too slow for 10Hz writes)
- ArrayList/LinkedList (causes GC pauses)
- LRU Cache (adds allocation overhead)

### Implementation

```kotlin
class MarketHistoryBuffer(capacity: Int = 3000) {
    private val history = Array(capacity) { MarketSnapshot.empty() }
    private var headIndex = 0
    
    fun add(snapshot: MarketSnapshot) {
        history[headIndex % capacity] = snapshot
        headIndex++
    }
}
```

### Consequences

**Pros:**
- ✅ Zero garbage collection pauses → smooth 60fps rendering
- ✅ Constant memory usage (~600KB for 3000 snapshots)
- ✅ O(1) read/write operations

**Cons:**
- ❌ Hard limit on history length (cannot extend without restart)
- ❌ Complexity in handling "wrap-around" logic for circular indexing
- ❌ Data loss on app crash (not persisted to disk)

**Mitigation**: For Pro users, we can increase buffer size to 36,000 (1 hour @ 10Hz) without architectural changes.

---

## ADR-002: Canvas-Only Rendering (No LazyColumn)

**Status**: ✅ Accepted  
**Date**: 2025-11-23  
**Deciders**: Core Team

### Context

Standard Android UI components (RecyclerView, LazyColumn) cause:
- Frame drops when updating 10 times per second
- Composition overhead (every update triggers recomposition)
- Object allocations in the render loop

### Decision

Use **Jetpack Compose Canvas API** exclusively for order book visualization. Draw directly to GPU without intermediate UI components.

### Implementation

```kotlin
Canvas(modifier = Modifier.fillMaxSize()) {
    // Direct GPU commands
    drawRect(color = bidColor, topLeft = Offset(0f, y), size = Size(barWidth, rowHeight))
}
```

### Consequences

**Pros:**
- ✅ Sustained 60fps @ 10Hz data updates
- ✅ Zero allocations in draw loop (colors/fonts pre-allocated with `remember`)
- ✅ Pixel-perfect control over visualization

**Cons:**
- ❌ No accessibility support (cannot use screen readers)
- ❌ More complex code than declarative UI
- ❌ Custom text rendering required (no built-in TextView)

**Business Impact**: This is our **technical moat**. Competitors using standard components cannot match our performance.

---

## ADR-003: Binary WebSocket Protocol (640 bytes/frame)

**Status**: ✅ Accepted  
**Date**: 2025-11-23  
**Deciders**: Core Team

### Context

Binance sends JSON (1.5KB/update). Parsing JSON on mobile:
- Consumes CPU (battery drain)
- Creates garbage (GC pauses)
- Requires reflection (slow)

### Decision

Backend parses Binance JSON → serializes to **fixed 640-byte binary format** → broadcasts to mobile clients.

### Protocol Specification

```
Bytes 0-159:   20 bid prices (double[20])
Bytes 160-319: 20 bid volumes (double[20])
Bytes 320-479: 20 ask prices (double[20])
Bytes 480-639: 20 ask volumes (double[20])
```

### Consequences

**Pros:**
- ✅ 60% bandwidth reduction (640B vs 1.5KB)
- ✅ Zero parsing overhead on mobile (direct `ByteBuffer.getDouble()`)
- ✅ Backend handles all exchange differences (multi-exchange support later)

**Cons:**
- ❌ Not human-readable (debugging harder)
- ❌ Tightly coupled protocol (backend/mobile must match versions)
- ❌ Fixed to 20 levels (cannot extend without protocol change)

**Mitigation**: Version field in WebSocket handshake for future protocol upgrades.

---

## ADR-004: Java 21 Virtual Threads for Backend

**Status**: ✅ Accepted  
**Date**: 2025-11-23  
**Deciders**: Core Team

### Context

Backend must handle:
- 1 WebSocket connection to Binance (inbound)
- N WebSocket connections to mobile clients (outbound broadcast)
- Potential for 1000+ concurrent users in the future

### Decision

Use **Java 21 Virtual Threads** instead of traditional thread pools or reactive frameworks (RxJava, Project Reactor).

### Implementation

```java
@EnableAsync
@Configuration
class AsyncConfig {
    @Bean
    AsyncTaskExecutor applicationTaskExecutor() {
        return new TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor());
    }
}
```

### Consequences

**Pros:**
- ✅ Simple imperative code (no reactive complexity)
- ✅ Handles 10,000+ concurrent connections with low memory
- ✅ No callback hell or backpressure management

**Cons:**
- ❌ Requires Java 21+ (deployment constraint)
- ❌ New technology (fewer Stack Overflow answers)

**Business Impact**: Can run on Oracle Cloud Free Tier (ARM Ampere A1) and handle scale without rewriting.

---

## ADR-005: OkHttp Instead of Scarlet for WebSocket

**Status**: ✅ Accepted (Revised)  
**Date**: 2025-11-23  
**Deciders**: Core Team

### Context

Initially attempted to use Scarlet (reactive WebSocket library) for better lifecycle management and automatic reconnection.

### Decision

**Reverted to OkHttp** due to:
- Scarlet library is unmaintained (last update 2018)
- Version conflicts with Kotlin 2.0
- Missing dependencies for modern AndroidX libraries

### Implementation

```kotlin
val client = OkHttpClient()
val webSocket = client.newWebSocket(request, object : WebSocketListener() {
    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        // Parse binary data
    }
})
```

### Consequences

**Pros:**
- ✅ Stable, maintained library (Square/OkHttp)
- ✅ Works with Kotlin 2.0
- ✅ Simple and well-documented

**Cons:**
- ❌ Manual reconnection logic required
- ❌ No built-in lifecycle management
- ❌ More boilerplate code

**Future**: When Scarlet 2.0 or similar library matures, we can migrate.

---

## Template for Future ADRs

```markdown
## ADR-XXX: [Decision Title]

**Status**: 🤔 Proposed / ✅ Accepted / ❌ Rejected / ⚠️ Deprecated  
**Date**: YYYY-MM-DD  
**Deciders**: [Team/Person]

### Context
[What is the problem we're trying to solve?]

### Decision
[What did we decide to do?]

### Implementation
[Code snippet or technical approach]

### Consequences
**Pros:**
- ✅ [Benefit 1]

**Cons:**
- ❌ [Drawback 1]

**Mitigation**: [How we handle the cons]
```

---

**Next ADR**: When we implement the heatmap (bucketing logic), we'll document ADR-006.
