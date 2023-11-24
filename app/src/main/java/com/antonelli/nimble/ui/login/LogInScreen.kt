package com.antonelli.nimble.ui.login

import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.antonelli.nimble.R
import com.antonelli.nimble.enums.StatesEnum
import com.antonelli.nimble.navigation.AppScreens
import com.antonelli.nimble.ui.loading.LoadingScreen

@Composable
fun LogInScreen(navController: NavController, logInViewModel: LogInViewModel = hiltViewModel()) {
    val isLoading by logInViewModel.isLoading.collectAsState()
    val loginResponse by logInViewModel.loginResponse.collectAsState()
    val fieldsError by logInViewModel.fieldsError.collectAsState()

    if (fieldsError) {
        val contexto = LocalContext.current
        Toast.makeText(contexto, stringResource(id = R.string.check_values), Toast.LENGTH_LONG).show()
        logInViewModel.fieldsError.value = false
    }

    if (loginResponse != null) {
        when (loginResponse!!) {
            StatesEnum.SUCCESS -> {
                navController.popBackStack()
                navController.navigate(AppScreens.HomeScreen.route)
                logInViewModel.loginResponse.value = null
            }
            StatesEnum.ERROR -> {
                navController.navigate(AppScreens.ErrorScreen.route + "/${AppScreens.LogInScreen.route}")
                logInViewModel.loginResponse.value = null
            }
            StatesEnum.START,
            StatesEnum.NOAUTH -> {
                logInViewModel.loginResponse.value = null
            }
        }
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
        if (isLoading == true) {
            LoadingScreen()
        } else {
            Spacer(modifier = Modifier.size(80.dp))
            Image(
                painter = painterResource(id = R.drawable.logo_white),
                contentDescription = "logo",
                Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.size(50.dp))
            EmailTextField(logInViewModel)
            Spacer(modifier = Modifier.size(25.dp))
            PasswordTextField(logInViewModel)
            Spacer(modifier = Modifier.size(25.dp))
            LogInButtom(fieldsError) { logInViewModel.logIn() }
        }
    }
}

@Composable
fun EmailTextField(logInViewModel: LogInViewModel) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = {
            logInViewModel.email.value = it
            text = it
        },
        placeholder = { Text(text = stringResource(id = R.string.email)) },
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1
    )
}

@Composable
fun PasswordTextField(logInViewModel: LogInViewModel) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = {
            logInViewModel.pass.value = it
            text = it
        },
        placeholder = { Text(text = stringResource(id = R.string.password)) },
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1
    )
}

@Composable
fun LogInButtom(enabledP: Boolean, logIn: () -> Unit) {
    var enabled by remember { mutableStateOf(enabledP) }
    Button(
        onClick = {
            enabled = false
            logIn()
        },
        enabled = !enabled,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        modifier = Modifier.size(300.dp, 50.dp)
    ) {
        Text(text = stringResource(id = R.string.login), fontWeight = FontWeight.Bold, color = Color.Black)
    }
}
