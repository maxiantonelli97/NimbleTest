package com.antonelli.nimble.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.antonelli.nimble.screens.HomeScreen
import com.antonelli.nimble.screens.SplashScreen
import com.antonelli.nimble.ui.login.ui.LogInScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.LogInScreen.route) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreens.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(AppScreens.LogInScreen.route) {
            LogInScreen(navController)
        }
    }
}
