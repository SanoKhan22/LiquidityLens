package com.liquiditylens.data

/**
 * Market Statistics Calculator
 * 
 * Provides real-time market metrics for order book analysis.
 */
data class MarketStats(
    val spread: Double,              // Absolute spread (ask - bid)
    val spreadPercent: Double,       // Spread as percentage of mid-price
    val midPrice: Double,            // Average of best bid and ask
    val bidAskRatio: Double,         // Ratio of total bid volume to ask volume
    val totalBidVolume: Double,      // Sum of all bid volumes
    val totalAskVolume: Double,      // Sum of all ask volumes
    val bestBid: Double,             // Best bid price
    val bestAsk: Double,             // Best ask price
    val depthAtBest: Double,         // Volume at best bid + best ask
    val imbalancePercent: Double     // (Bid - Ask) / (Bid + Ask) * 100
)

/**
 * Calculate market statistics from a snapshot.
 */
fun calculateMarketStats(snapshot: MarketSnapshot, levels: Int = 10): MarketStats {
    val bestBid = snapshot.bidPrices[0]
    val bestAsk = snapshot.askPrices[0]
    
    val spread = bestAsk - bestBid
    val midPrice = (bestBid + bestAsk) / 2.0
    val spreadPercent = (spread / midPrice) * 100.0
    
    // Calculate total volumes for specified levels
    val totalBidVolume = snapshot.bidVolumes.take(levels).sum()
    val totalAskVolume = snapshot.askVolumes.take(levels).sum()
    
    val bidAskRatio = if (totalAskVolume > 0) totalBidVolume / totalAskVolume else 0.0
    
    // Depth at best prices
    val depthAtBest = snapshot.bidVolumes[0] + snapshot.askVolumes[0]
    
    // Order book imbalance: positive = more bids, negative = more asks
    val totalVolume = totalBidVolume + totalAskVolume
    val imbalancePercent = if (totalVolume > 0) {
        ((totalBidVolume - totalAskVolume) / totalVolume) * 100.0
    } else {
        0.0
    }
    
    return MarketStats(
        spread = spread,
        spreadPercent = spreadPercent,
        midPrice = midPrice,
        bidAskRatio = bidAskRatio,
        totalBidVolume = totalBidVolume,
        totalAskVolume = totalAskVolume,
        bestBid = bestBid,
        bestAsk = bestAsk,
        depthAtBest = depthAtBest,
        imbalancePercent = imbalancePercent
    )
}
