package com.antonelli.nimble.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.antonelli.nimble.R
import com.antonelli.nimble.navigation.AppScreens

@Composable
fun LogInScreen(navController: NavController, logInViewModel: LogInViewModel = hiltViewModel()) {
    val email by logInViewModel.email.observeAsState(initial = "")
    val pass by logInViewModel.pass.observeAsState(initial = "")
    val logInResult by logInViewModel.logInResult.observeAsState(initial = false)

    if (logInResult) {
        navController.popBackStack()
        navController.navigate(AppScreens.HomeScreen.route)
    } else {
        // TODO
    }

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
        EmailTextField(email)
        Spacer(modifier = Modifier.size(25.dp))
        PasswordTextField(pass)
        Spacer(modifier = Modifier.size(25.dp))
        LogInButtom() { logInViewModel.logIn() }
    }
}

@Composable
fun EmailTextField(email: String) {
    TextField(
        value = email,
        onValueChange = {
            // NotImplemented
        },
        placeholder = { Text(text = "Email") },
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1
    )
}

@Composable
fun PasswordTextField(pass: String) {
    TextField(
        value = pass,
        onValueChange = {
            // NotImplemented
        },
        placeholder = { Text(text = "Password") },
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1
    )
}

@Composable
fun LogInButtom(logIn: () -> Unit) {
    Button(
        onClick = {
            logIn()
        },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        modifier = Modifier.size(300.dp, 50.dp)
    ) {
        Text(text = "Log In", fontWeight = FontWeight.Bold, color = Color.Black)
    }
}
