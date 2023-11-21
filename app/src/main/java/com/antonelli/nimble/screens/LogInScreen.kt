package com.antonelli.nimble.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.antonelli.nimble.R

@Composable
fun LogInScreen(navController: NavController) {
    Column(
        modifier = Modifier.paint(
            painterResource(id = R.drawable.background),
            colorFilter = ColorFilter.tint(Color(0.0f, 0.0f, 0.0f, 0.8f), blendMode = BlendMode.Darken),
            contentScale = ContentScale.FillBounds
        ).fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(80.dp))
        Image(
            painter = painterResource(id = R.drawable.logo_white),
            contentDescription = "logo",
            Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.size(50.dp))
        CustomTextField(hint = "Email")
        Spacer(modifier = Modifier.size(25.dp))
        CustomTextField(hint = "Password")
        Spacer(modifier = Modifier.size(25.dp))
        CustomButtom()
    }
}

@Composable
fun CustomTextField(hint: String) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        },
        placeholder = { Text(text = hint) },
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
fun CustomButtom() {
    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        modifier = Modifier.size(300.dp, 50.dp)
    ) {
        Text(text = "Log In", fontWeight = FontWeight.Bold, color = Color.Black)
    }
}
