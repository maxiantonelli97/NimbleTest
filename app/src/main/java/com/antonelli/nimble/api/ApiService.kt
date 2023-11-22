package com.antonelli.nimble.api

import com.antonelli.nimble.entity.LogInModel
import com.antonelli.nimble.entity.LogInResponseModel
import com.antonelli.nimble.entity.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("oauth/token")
    suspend fun logIn(@Body value: LogInModel): ResponseBody<LogInResponseModel>
}
