# Domain Logic - LiquidityLens

> **Purpose**: Explain the financial mathematics and business rules so non-technical stakeholders (investors, partners) can understand how the product works.

---

## 1. Core Concepts

### 1.1 What is an Order Book?

An **Order Book** is a real-time list of all pending buy/sell orders for an asset (e.g., Bitcoin).

```
╔═══════════════════════════════════════╗
║           ORDER BOOK (BTC/USDT)        ║
╠═══════════════════════════════════════╣
║  SELL ORDERS (Asks)  │  Price  │ Vol ║
║──────────────────────┼─────────┼──────║
║                      │ 90,050  │ 1.2  ║
║                      │ 90,040  │ 0.8  ║
║                      │ 90,030  │ 2.5  ║
║──────────────────────┴─────────┴──────║
║         SPREAD ($20) ↑                ║
║──────────────────────┬─────────┬──────║
║                      │ 90,010  │ 1.5  ║
║                      │ 90,000  │ 3.2  ║
║  BUY ORDERS (Bids)   │ 89,990  │ 0.9  ║
╚══════════════════════╧═════════╧══════╝
```

**Business Rule**: The **Spread** is the difference between the lowest Ask and highest Bid. A small spread (<0.1%) means high liquidity (easy to trade). Large spread (>1%) means low liquidity (risky).

---

## 2. Key Metrics (The "What" We Calculate)

### 2.1 Order Book Imbalance

**Definition**: Measures buying vs selling pressure.

```
Imbalance = (TotalBidVolume - TotalAskVolume) / (TotalBidVolume + TotalAskVolume)
```

**Range**: -1.0 to +1.0

| Imbalance | Meaning | UI Indicator |
|-----------|---------|--------------|
| +0.7 to +1.0 | Strong buy pressure | 🟢 Green bar (flash) |
| +0.3 to +0.7 | Moderate buy pressure | 🟢 Green bar |
| -0.3 to +0.3 | Balanced | ⚪ White bar |
| -0.7 to -0.3 | Moderate sell pressure | 🔴 Red bar |
| -1.0 to -0.7 | Strong sell pressure | 🔴 Red bar (flash) |

**Business Logic**: If imbalance suddenly shifts from +0.8 to -0.8, it signals a potential price crash (large sell order appeared).

---

### 2.2 Liquidity Depth

**Definition**: Total volume available within X% of current price.

```
Depth(±1%) = Sum of all bids/asks within 1% of mid-price
```

**Example**:
```
Current Price: $90,000
±1% Range: $89,100 - $90,900

Bid Depth: 45.2 BTC (buy support)
Ask Depth: 32.1 BTC (sell resistance)
```

**Business Rule**: If Depth < 10 BTC, the market is "Thin" → high slippage risk → warn the user.

---

### 2.3 Spread Percentage

```
Spread% = ((LowestAsk - HighestBid) / MidPrice) × 100
```

**Thresholds**:
- **Tight Market**: <0.01% (crypto exchanges during normal hours)
- **Normal Market**: 0.01% - 0.1%
- **Wide Market**: 0.1% - 1% (low liquidity altcoins)
- **Broken Market**: >1% (potential data issue or extreme volatility)

**Business Rule**: If `Spread% > 1%`, display a warning: "⚠️ Wide Spread - Low Liquidity"

---

## 3. Critical Business Rules (The "Must-Haves")

### 3.1 Data Integrity Rule: No Crossed Markets

**Problem**: A "Crossed Market" occurs when `Best Bid ≥ Best Ask`. This is impossible in a functioning market (arbitrage would instantly fix it).

**Causes**:
- Stale data (WebSocket lag > 500ms)
- Exchange API bug
- Network issues

**Business Rule**:
```kotlin
if (bestBid >= bestAsk) {
    // Invalid data detected!
    uiState = UIState.STALE_DATA
    desaturateUI(alpha = 0.5f) // Turn UI grey
    showWarning("⚠️ Data may be stale")
}
```

**Implementation Status**: ⏳ **TO-DO** (Priority: HIGH)

---

### 3.2 Market DVR Logic (Our USP)

The **Market DVR** feature allows users to "rewind" and analyze past market conditions.

#### Live Mode
```kotlin
mode = LIVE
uiDisplay = ringBuffer.getLatest() // Always show newest data
ringBuffer.append(newSnapshot)    // Keep recording in background
```

#### Replay Mode (User Scrubs)
```kotlin
mode = REPLAY(offset = 150) // 150 snapshots ago (~15 seconds)
uiDisplay = ringBuffer.get(offset)  // Show historical data
ringBuffer.append(newSnapshot)      // Still recording live data!
```

#### Snap-to-Live Logic
```kotlin
if (scrubber.position >= ringBuffer.size - 1) {
    mode = LIVE // User dragged to extreme right
    uiDisplay = ringBuffer.getLatest()
}
```

**Business Value**: This is your **patent-able feature**. No other mobile trading app has "DVR for markets."

---

### 3.3 Heatmap Bucketing Logic

**Problem**: Mobile screens can only show ~50 price levels. Bitcoin has 1000+ active levels.

**Solution**: Group (bucket) prices by zoom level.

#### Bucketing Algorithm

```kotlin
fun bucketPrice(price: Double, zoomLevel: Int): Double {
    val bucketSize = when (zoomLevel) {
        1 -> 100.0  // $100 buckets (zoomed out)
        2 -> 50.0   // $50 buckets
        3 -> 10.0   // $10 buckets (default)
        4 -> 1.0    // $1 buckets (zoomed in)
        else -> 10.0
    }
    return (price / bucketSize).roundToInt() * bucketSize
}
```

**Example**:
```
Raw Data:          Bucketed ($10):
$90,001.23  ─┐
$90,002.45  ─┼─→  $90,000 (combined volume)
$90,003.78  ─┘
$90,011.56  ──→  $90,010
```

#### Color Intensity Logic

**Bad Approach** (don't do this):
```kotlin
color = lerp(white, red, volume / GLOBAL_MAX_VOLUME)
```
→ Small liquidity walls become invisible.

**Good Approach** (relative to visible range):
```kotlin
val visibleMax = visibleLevels.maxOf { it.volume }
color = lerp(white, red, volume / visibleMax)
```
→ Local liquidity walls always stand out.

**Business Rule**: Heatmap intensity is **relative to the currently visible price range**, not global max.

**Implementation Status**: ⏳ **TO-DO** (Priority: MEDIUM)

---

## 4. Latency Thresholds (Performance SLAs)

| Metric | Threshold | Action if Exceeded |
|--------|-----------|-------------------|
| WebSocket Latency | 500ms | Desaturate UI, show warning |
| Frame Time | 16ms (60fps) | Skip frame, log performance metric |
| Buffer Overrun | Ring buffer full | Oldest data dropped (expected behavior) |
| Data Staleness | Last update >2s | Display "⏸️ Paused" indicator |

---

## 5. Hungarian Market Specifics

### 5.1 Localization Rules

- **Currency**: Display USDT (stablecoin) prices, not HUF
- **Number Format**: European (comma for decimals: `90.000,50`)
- **Time Zone**: CET/CEST (Budapest)

### 5.2 Target User Segments (Hungary)

1. **University Students (CS/Economics)** - Free tier users
   - Use case: Learning about market microstructure
   - Retention driver: Export charts for assignments

2. **Crypto Day Traders** - Pro tier candidates
   - Use case: Analyzing altcoin pumps/dumps
   - Upgrade trigger: 1-hour DVR history

3. **Small Crypto Exchanges** - B2B customers
   - Use case: White-label visualization for their apps
   - Revenue: API licensing

---

## 6. Future Domain Logic (Roadmap)

### 6.1 Whale Alert Detection

```
if (single_order_volume > 10 × average_order_volume) {
    notify("🐋 Large Order Detected: ${volume} BTC @ $${price}")
}
```

### 6.2 Support/Resistance Detection

Identify price levels with high historical volume:
```
support_level = price where bid_volume_history.max() occurred
resistance_level = price where ask_volume_history.max() occurred
```

### 6.3 Multi-Exchange Aggregation

Combine order books from Binance, Coinbase, Kraken:
```
aggregated_volume[price] = sum(binance[price], coinbase[price], kraken[price])
```

---

## 7. Glossary for Non-Technical Stakeholders

| Term | Definition |
|------|------------|
| **Bid** | Buy order (someone wants to buy at this price) |
| **Ask** | Sell order (someone wants to sell at this price) |
| **Spread** | Difference between best bid and ask |
| **Liquidity** | How easy it is to trade without moving the price |
| **Slippage** | Difference between expected price and actual trade price |
| **Market Depth** | Total volume available near current price |
| **Order Book Imbalance** | Ratio of buy vs sell pressure |
| **Ring Buffer** | Circular memory structure for DVR |
| **Canvas Rendering** | Drawing directly to GPU (faster than UI components) |

---

**Living Document**: Update this as we add features (heatmap, whale alerts, etc.).
