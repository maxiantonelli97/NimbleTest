package com.antonelli.nimble.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.antonelli.nimble.enums.StatesEnum
import com.antonelli.nimble.navigation.AppScreens
import com.antonelli.nimble.ui.loading.LoadingScreen

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {
    val responseEnum by homeViewModel.responseEnum.collectAsState()
    val isLoading by homeViewModel.isLoading.collectAsState()

    if (isLoading == true) {
        LoadingScreen()
    }

    if (responseEnum != null) {
        when (responseEnum!!) {
            StatesEnum.SUCCESS -> {
                Carousel(
                    arrayListOf(
                        homeViewModel.surveys!![0].attributes?.cover_image_url ?: "",
                        "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_",
                        "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_"
                    )
                )
            }
            StatesEnum.ERROR -> {
                // navController.clearBackStack(AppScreens.ErrorScreen.route + "/${AppScreens.HomeScreen.route}")
                navController.navigate(AppScreens.ErrorScreen.route + "/${AppScreens.HomeScreen.route}")
                homeViewModel.responseEnum.value = null
            }
            StatesEnum.START -> {
                homeViewModel.getSurveys()
                homeViewModel.responseEnum.value = null
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel(images: List<String>) {
    Column(
        modifier = Modifier.background(Color.Black).fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            pageCount = images.size,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            AsyncImage(
                model = images[page],
                contentDescription = page.toString(),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}
