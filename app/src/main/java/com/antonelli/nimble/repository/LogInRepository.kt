package com.antonelli.nimble.repository

import com.antonelli.nimble.entity.LogInModel
import com.antonelli.nimble.entity.LogInResponseModel
import com.antonelli.nimble.entity.ResponseBody
import retrofit2.Response

interface LogInRepository {
    suspend fun logIn(logInModel: LogInModel): Response<ResponseBody<LogInResponseModel>>
}
