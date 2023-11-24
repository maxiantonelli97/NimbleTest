package com.antonelli.nimble.ui.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonelli.nimble.enums.StatesEnum
import com.antonelli.nimble.repository.LogInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val repository: LogInRepository) : ViewModel() {

    var email = MutableStateFlow("")

    var pass = MutableStateFlow("")

    var loginResponse = MutableStateFlow<StatesEnum?>(null)

    var isLoading = MutableStateFlow<Boolean?>(false)

    var fieldsError = MutableStateFlow(false)

    fun logIn() {
        if (isValidPassword() && isValidEmail()) {
            fieldsError.value = false
            viewModelScope.launch {
                isLoading.value = true
                try {
                    val response = repository.logIn(email.value, pass.value)
                    if (response) {
                        isLoading.value = null
                        loginResponse.value = StatesEnum.SUCCESS
                    } else {
                        isLoading.value = null
                        loginResponse.value = StatesEnum.ERROR
                    }
                } catch (e: Exception) {
                    isLoading.value = null
                    loginResponse.value = StatesEnum.ERROR
                }
            }
        } else {
            fieldsError.value = true
        }
    }

    private fun isValidPassword(): Boolean = pass.value.length > 7

    private fun isValidEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
}
