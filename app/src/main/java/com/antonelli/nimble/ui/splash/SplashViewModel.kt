package com.antonelli.nimble.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonelli.nimble.repository.AuthRepository
import com.antonelli.nimble.repository.LogInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val logInRepository: LogInRepository, private val authRepository: AuthRepository) : ViewModel() {

    var goToLogin = MutableStateFlow<Boolean?>(null)
    fun goToLogin() {
        viewModelScope.launch {
            if (authRepository.isTokenValido()) {
                goToLogin.value = false
            } else {
                goToLogin.value = !logInRepository.refreshToken()
            }
        }
    }
}
