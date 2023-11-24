package com.antonelli.nimble.repository

import com.antonelli.nimble.entity.ResponseBody
import com.antonelli.nimble.entity.SurveysResponseModel

interface HomeRepository {
    suspend fun getSurveys(params: HashMap<String, Any>): ResponseBody<ArrayList<SurveysResponseModel>>?
}
