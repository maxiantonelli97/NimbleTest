package com.antonelli.nimble.repository

import com.antonelli.nimble.entity.LogInModel
import com.antonelli.nimble.entity.LogInResponseModel
import com.antonelli.nimble.entity.ResponseBody

interface LogInRespository {
    suspend fun logIn(logInModel: LogInModel): ResponseBody<LogInResponseModel>
}
