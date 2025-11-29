# Changelog

All notable changes to LiquidityLens will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [Unreleased]

### Planned
- Heatmap visualization with price bucketing
- Data integrity checks (crossed market detection)
- Share snapshot feature (PNG export with branding)
- Pro tier with 1-hour DVR
- Multi-exchange support (Coinbase, Kraken)

---

## [0.3.0] - 2025-11-23

### Added
- **Management Scripts**: Backend startup/shutdown scripts with automatic port conflict resolution
- **Documentation**: ADR (Architecture Decision Records) with 5 major decisions documented
- **Documentation**: DOMAIN_LOGIC.md explaining financial mathematics for non-technical stakeholders
- **Documentation**: Business strategy document with Hungarian market focus

### Changed
- Reverted from Scarlet to OkHttp WebSocket client due to library compatibility issues
- Updated backend startup to explicitly use Java 21 to avoid version conflicts

### Fixed
- Build errors caused by incompatible Scarlet library dependencies
- Backend startup failures when port 8080 was already in use
- Java version mismatch (backend requires Java 21 but system defaulted to Java 17)

---

## [0.2.0] - 2025-11-23

### Added
- **Market DVR Controls**: Complete time-travel interface with pause/replay/scrub
  - Frame-by-frame navigation (Prev/Next buttons)
  - Jump ±10 seconds buttons
  - Slider for quick scrubbing through history
  - Live/Replay indicator with status display
  - Frame counter and time-ago indicator
- **Ring Buffer Validation**: Minimum 50 snapshots required before DVR activates
- **Buffer Status Display**: Shows current buffer size, capacity, and memory usage

### Changed
- Enhanced DVR UI with more granular controls for precise market analysis
- Improved slider UX with time range indicators

---

## [0.1.0] - 2025-11-23 (MVP Release)

### Added
- **Backend** (Java 21 + Spring Boot):
  - Connects to Binance WebSocket API (`wss://stream.binance.com:9443/ws/btcusdt@depth20@100ms`)
  - Zero-GC design using pre-allocated `double[]` arrays
  - Binary protocol broadcasting (640 bytes/frame)
  - Virtual Threads for handling concurrent mobile connections
  
- **Android App** (Kotlin 2.0 + Jetpack Compose):
  - Real-time order book visualization using Canvas API (no LazyColumn)
  - Ring Buffer implementation (3000 slots = ~5 minutes @ 10Hz)
  - OkHttp WebSocket client with StateFlow integration
  - Zero-allocation draw loop (60fps capable)
  
- **UI/UX**:
  - Institutional cyberpunk theme (#0D1117 dark background)
  - Neon green bids (#00E676) / neon red asks (#FF1744)
  - Monospace typography (FontFamily.Monospace) for perfect number alignment
  - 10 levels of bids/asks displayed with volume bars
  
- **Market DVR** (Time-travel):
  - Circular buffer stores last 5 minutes of market data
  - Pause live updates and scrub through history
  - Replay controls with slider interface
  
### Technical Achievements
- ✅ Zero garbage collection pauses in render loop
- ✅ Sustained 60fps @ 10Hz data updates
- ✅ ~600KB memory footprint for 3000 snapshots
- ✅ <100ms WebSocket latency (Binance → Backend → Mobile)

### Known Limitations
- Single trading pair (BTC/USDT only)
- 5-minute history limit (hardcoded ring buffer size)
- No data persistence (history lost on app restart)
- No authentication or user accounts
- HTTP only (no TLS/WSS)
- No error recovery for network failures

---

## Version History Summary

| Version | Date | Key Features |
|---------|------|--------------|
| 0.3.0 | 2025-11-23 | Management scripts, comprehensive documentation, business strategy |
| 0.2.0 | 2025-11-23 | Enhanced Market DVR controls (frame-by-frame, ±10s jumps) |
| 0.1.0 | 2025-11-23 | MVP: Working order book visualization + Market DVR |

---

## Upcoming Releases

### [0.4.0] - Planned
**Theme**: Data Integrity & Polish

- [ ] Crossed market detection (ADR-006)
- [ ] WebSocket latency monitoring (<500ms SLA)
- [ ] Stale data UI indicators (grey-out when lag detected)
- [ ] Heatmap visualization (price bucketing)
- [ ] Share snapshot feature (export PNG with branding)

### [0.5.0] - Planned
**Theme**: Monetization & Growth

- [ ] Pro tier unlock (1-hour DVR via in-app purchase)
- [ ] Altcoin pairs (ETH/USDT, SOL/USDT)
- [ ] User analytics (time spent, feature usage)
- [ ] Play Store release (Open Beta)

### [1.0.0] - Planned
**Theme**: Production Ready

- [ ] User authentication (Firebase Auth)
- [ ] Cloud deployment (Oracle Cloud Free Tier)
- [ ] TLS/WSS encryption
- [ ] Automatic reconnection logic
- [ ] Crash reporting (Firebase Crashlytics)
- [ ] Load testing (100+ concurrent users)

---

## Migration Notes

### 0.2.0 → 0.3.0
- No breaking changes
- New management scripts in project root: `start-backend.sh`, `stop-backend.sh`

### 0.1.0 → 0.2.0
- No breaking changes
- Enhanced UI controls (backward compatible)

---

**Maintenance Policy**: We follow semantic versioning (MAJOR.MINOR.PATCH):
- **MAJOR**: Breaking API changes
- **MINOR**: New features (backward compatible)
- **PATCH**: Bug fixes only

**Support**: Only the latest minor version receives updates.
