package com.antonelli.nimble.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonelli.nimble.entity.LogInModel
import com.antonelli.nimble.repository.LogInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val repository: LogInRepository) : ViewModel() {

    private val _email = MutableLiveData<String>("dev@example.com") // TODO delete hardtext
    val email: LiveData<String> = _email

    private val _pass = MutableLiveData<String>("password") // TODO delete hardtext
    val pass: LiveData<String> = _pass

    private val _logInResult = MutableLiveData<Boolean>()
    val logInResult: LiveData<Boolean> = _logInResult

    fun logIn() {
        if (isValidPassword() && isValidEmail()) {
            viewModelScope.launch {
                val response = repository.logIn(LogInModel(email = email.value, password = pass.value))
                _logInResult.value = response.body()?.data?.id != null
            }
        } else {
            _logInResult.value = false
        }
    }

    private fun isValidPassword(): Boolean = (pass.value?.length ?: 0) > 7

    private fun isValidEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(email.value ?: "").matches()
}
