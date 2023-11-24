package com.antonelli.nimble.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonelli.nimble.entity.SurveyItem
import com.antonelli.nimble.entity.SurveysResponseModel
import com.antonelli.nimble.enums.StatesEnum
import com.antonelli.nimble.repository.AuthRepository
import com.antonelli.nimble.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository, private val authRepository: AuthRepository) : ViewModel() {

    private var surveys: ArrayList<SurveysResponseModel>? = null

    var responseEnum = MutableStateFlow<StatesEnum?>(StatesEnum.START)

    var isLoading = MutableStateFlow<Boolean?>(false)

    var isRefreshing = MutableStateFlow(false)

    fun getSurveys() {
        surveys = arrayListOf()
        viewModelScope.launch {
            isLoading.value = true
            val params = hashMapOf<String, Any>()
            params["page\\[number\\]"] = 1
            params["page\\[size\\]"] = 5
            try {
                val response = repository.getSurveys(params)
                isRefreshing.value = false
                if (response?.data != null) {
                    if (response.auth) {
                        isLoading.value = null
                        surveys = response.data
                        responseEnum.value = StatesEnum.SUCCESS
                    } else {
                        isLoading.value = null
                        responseEnum.value = StatesEnum.NOAUTH
                    }
                } else {
                    isLoading.value = null
                    responseEnum.value = StatesEnum.ERROR
                }
            } catch (e: Exception) {
                isLoading.value = null
                isRefreshing.value = false
                responseEnum.value = StatesEnum.ERROR
            }
        }
    }

    fun getSurveysList(): ArrayList<SurveyItem> {
        val aux = arrayListOf<SurveyItem>()
        surveys?.forEach {
            it.attributes?.let { it1 -> aux.add(it1) }
        }
        return aux
    }

    fun logOut() {
        viewModelScope.launch {
            authRepository.setToken(null)
            authRepository.setValid(null)
            responseEnum.value = StatesEnum.NOAUTH
        }
    }
}
