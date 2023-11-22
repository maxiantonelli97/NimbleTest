package com.antonelli.nimble.repository

import com.antonelli.nimble.api.ApiService
import com.antonelli.nimble.entity.LogInModel
import com.antonelli.nimble.entity.LogInResponseModel
import com.antonelli.nimble.entity.ResponseBody
import javax.inject.Inject

class LogInRespositoryImp @Inject constructor(private val apiService: ApiService) : LogInRespository {
    override suspend fun logIn(logInModel: LogInModel): ResponseBody<LogInResponseModel> = apiService.logIn(logInModel)
}
