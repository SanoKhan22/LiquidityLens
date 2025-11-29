package com.liquiditylens.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Bottom Navigation Destinations
 */
sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Chart : Screen("chart", "Chart", Icons.Default.Home)
    object OrderBook : Screen("orderbook", "Order Book", Icons.Default.Menu)
    object Settings : Screen("settings", "Settings", Icons.Default.Settings)
}

val bottomNavItems = listOf(
    Screen.Chart,
    Screen.OrderBook,
    Screen.Settings
)
