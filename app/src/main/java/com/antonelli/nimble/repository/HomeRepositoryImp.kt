package com.antonelli.nimble.repository

import com.antonelli.nimble.api.ApiService
import com.antonelli.nimble.entity.ResponseBody
import com.antonelli.nimble.entity.SurveysResponseModel
import retrofit2.Response
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(private val apiService: ApiService) : HomeRepository {
    override suspend fun getSurveys(params: HashMap<String, Any>, token: String): Response<ResponseBody<ArrayList<SurveysResponseModel>>> =
        apiService.getSurveys(params, token)
}
