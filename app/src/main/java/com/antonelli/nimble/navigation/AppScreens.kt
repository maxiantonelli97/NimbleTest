package com.antonelli.nimble.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen : AppScreens("splash_screen")
    object HomeScreen : AppScreens("home_screen")
    object LogInScreen : AppScreens("log_in_screen")
}
