package com.antonelli.nimble.ui.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.antonelli.nimble.R

@Composable
fun ErrorScreen(navController: NavController, start: String) {
    Column(
        modifier = Modifier.background(Color.Black).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_warning_24),
            contentDescription = "warning",
            Modifier.size(200.dp).clickable {
                navController.popBackStack()
                navController.navigate(start)
            }
        )
        Text(
            text = "Toque para intentar nuevamente",
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
