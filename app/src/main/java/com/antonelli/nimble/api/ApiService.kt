package com.antonelli.nimble.api

import com.antonelli.nimble.entity.LogInModel
import com.antonelli.nimble.entity.LogInResponseModel
import com.antonelli.nimble.entity.ResponseBody
import com.antonelli.nimble.entity.SurveysResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface ApiService {
    @POST("oauth/token")
    suspend fun logIn(@Body value: LogInModel): Response<ResponseBody<LogInResponseModel>>

    @GET("surveys")
    suspend fun getSurveys(
        @QueryMap params: HashMap<String, Any>,
        @Header("Authorization") token: String
    ): Response<ResponseBody<ArrayList<SurveysResponseModel>>>
}
