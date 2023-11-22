package com.antonelli.nimble.repository

import com.antonelli.nimble.entity.ResponseBody
import com.antonelli.nimble.entity.SurveysResponseModel
import retrofit2.Response

interface HomeRepository {
    suspend fun getSurveys(params: HashMap<String, Any>, token: String): Response<ResponseBody<ArrayList<SurveysResponseModel>>>
}
