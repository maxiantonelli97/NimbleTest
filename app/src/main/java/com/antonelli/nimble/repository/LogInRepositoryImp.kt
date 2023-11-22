package com.antonelli.nimble.repository

import com.antonelli.nimble.api.ApiService
import com.antonelli.nimble.entity.LogInModel
import com.antonelli.nimble.entity.LogInResponseModel
import com.antonelli.nimble.entity.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class LogInRepositoryImp @Inject constructor(private val apiService: ApiService) : LogInRepository {
    override suspend fun logIn(logInModel: LogInModel): Response<ResponseBody<LogInResponseModel>> = apiService.logIn(logInModel)
}
