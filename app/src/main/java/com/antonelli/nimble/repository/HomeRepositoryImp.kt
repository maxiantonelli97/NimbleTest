package com.antonelli.nimble.repository

import com.antonelli.nimble.api.ApiService
import com.antonelli.nimble.entity.ResponseBody
import com.antonelli.nimble.entity.SurveysResponseModel
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val authRepository: AuthRepository,
    private val logInRepository: LogInRepository
) : HomeRepository {
    override suspend fun getSurveys(params: HashMap<String, Any>):
        ResponseBody<ArrayList<SurveysResponseModel>>? {
        try {
            val token = authRepository.getToken()
            return if (authRepository.isTokenValido() && token != null) {
                val response = apiService.getSurveys(params, token)
                if (response != null && response.isSuccessful && response.body() != null) {
                    response.body()
                } else {
                    null
                }
            } else {
                if (logInRepository.refreshToken()) {
                    getSurveys(params)
                } else {
                    ResponseBody(arrayListOf(), false)
                }
            }
        } catch (e: Exception) {
            return null
        }
    }
}
