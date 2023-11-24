package com.antonelli.nimble.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.antonelli.nimble.ui.detail.DetailScreen
import com.antonelli.nimble.ui.error.ErrorScreen
import com.antonelli.nimble.ui.home.HomeScreen
import com.antonelli.nimble.ui.login.LogInScreen
import com.antonelli.nimble.ui.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreens.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(AppScreens.LogInScreen.route) {
            LogInScreen(navController)
        }
        composable(
            AppScreens.ErrorScreen.route + "/{start}",
            arguments = listOf(navArgument("start") { type = NavType.StringType })
        ) { backStackEntry ->
            ErrorScreen(navController, backStackEntry.arguments?.getString("start") ?: AppScreens.HomeScreen.route)
        }
        composable(
            AppScreens.DetailScreen.route + "/{detail}",
            arguments = listOf(navArgument("detail") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailScreen(navController, backStackEntry.arguments?.getString("detail") ?: AppScreens.DetailScreen.route)
        }
    }
}
