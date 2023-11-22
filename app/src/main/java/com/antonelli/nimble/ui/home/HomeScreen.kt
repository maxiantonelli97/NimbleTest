package com.antonelli.nimble.ui.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {
    val surveys by homeViewModel.surveys.observeAsState(initial = null)

    homeViewModel.getSurveys()

    if (surveys != null) {
        Log.d("FUNCIONO", "FUNCIONO")
    }
}
