package com.liquiditylens.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.liquiditylens.data.MarketDataWebSocket
import com.liquiditylens.ui.chart.ChartScreen
import com.liquiditylens.ui.orderbook.OrderBookScreen
import com.liquiditylens.ui.settings.SettingsScreen

/**
 * Main App with Bottom Navigation
 */
@Composable
fun LiquidityLensApp(
    orderBookWebSocket: MarketDataWebSocket  // Existing order book WebSocket
) {
    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF0D1117),
                contentColor = Color(0xFFE6EDF3)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                
                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF00E676),
                            selectedTextColor = Color(0xFF00E676),
                            indicatorColor = Color(0xFF00E676).copy(alpha = 0.2f),
                            unselectedIconColor = Color(0xFF8B949E),
                            unselectedTextColor = Color(0xFF8B949E)
                        )
                    )
                }
            }
        },
        containerColor = Color(0xFF0D1117)
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Chart.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Chart.route) {
                ChartScreen()
            }
            composable(Screen.OrderBook.route) {
                OrderBookScreen(webSocket = orderBookWebSocket)
            }
            composable(Screen.Settings.route) {
                SettingsScreen()
            }
        }
    }
}
