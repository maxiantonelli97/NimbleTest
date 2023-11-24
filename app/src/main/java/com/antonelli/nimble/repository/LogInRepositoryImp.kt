package com.antonelli.nimble.repository

import com.antonelli.nimble.api.ApiService
import com.antonelli.nimble.entity.LogInModel
import javax.inject.Inject

class LogInRepositoryImp @Inject constructor(
    private var logInModel: LogInModel,
    private val apiService: ApiService,
    private val authRepository: AuthRepository
) : LogInRepository {
    override suspend fun logIn(email: String, pass: String): Boolean {
        logInModel.grant_type = "password"
        logInModel.email = email
        logInModel.password = pass

        val response = apiService.logIn(logInModel)

        return if (response.isSuccessful && response.body()?.data != null) {
            authRepository.setToken(response.body()!!.data!!.attributes?.access_token)
            val aux = (response.body()!!.data!!.attributes?.expires_in ?: 0.0) + (response.body()!!.data!!.attributes?.created_at ?: 0.0)
            authRepository.setValid(aux)
            true
        } else {
            authRepository.setToken(null)
            authRepository.setValid(null)
            false
        }
    }
    override suspend fun refreshToken(): Boolean {
        logInModel.grant_type = "refresh_token"

        return try {
            val response = apiService.refreshToken(logInModel)
            if (response.isSuccessful && response.body()?.data != null) {
                authRepository.setToken(response.body()!!.data!!.attributes?.access_token)
                val aux = (response.body()!!.data!!.attributes?.expires_in ?: 0.0) + (response.body()!!.data!!.attributes?.created_at ?: 0.0)
                authRepository.setValid(aux)
                true
            } else {
                authRepository.setToken(null)
                authRepository.setValid(null)
                false
            }
        } catch (e: Exception) {
            false
        }
    }
}
