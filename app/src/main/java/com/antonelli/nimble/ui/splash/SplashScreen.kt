package com.antonelli.nimble.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.antonelli.nimble.R
import com.antonelli.nimble.navigation.AppScreens

@Composable
fun SplashScreen(navController: NavController, splashViewModel: SplashViewModel = hiltViewModel()) {
    val goToLogin by splashViewModel.goToLogin.collectAsState(null)
    splash()
    LaunchedEffect(Unit) {
        splashViewModel.goToLogin()
    }

    if (goToLogin != null) {
        if (goToLogin == true) {
            splashViewModel.goToLogin.value = null
            navController.popBackStack()
            navController.navigate(AppScreens.LogInScreen.route)
        } else {
            splashViewModel.goToLogin.value = null
            navController.popBackStack()
            navController.navigate(AppScreens.HomeScreen.route)
        }
    }
}

@Composable
fun splash() {
    Column(
        modifier = Modifier.paint(painterResource(id = R.drawable.background), contentScale = ContentScale.FillBounds).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_white),
            contentDescription = "logo",
            Modifier.size(200.dp)
        )
    }
}
