package com.antonelli.nimble.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonelli.nimble.entity.SurveysResponseModel
import com.antonelli.nimble.enums.StatesEnum
import com.antonelli.nimble.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    var surveys: ArrayList<SurveysResponseModel>? = null

    var responseEnum = MutableStateFlow<StatesEnum?>(StatesEnum.START)

    var isLoading = MutableStateFlow<Boolean?>(false)

    fun getSurveys() {
        viewModelScope.launch {
            isLoading.value = true
            val params = hashMapOf<String, Any>()
            params["page"] = 1
            params["page_size"] = 5
            try {
                val response = repository.getSurveys(params, "Bearer 123") // TODO manage token
                if (response.isSuccessful && response.body()?.data != null) {
                    isLoading.value = null
                    surveys = response.body()!!.data
                    responseEnum.value = StatesEnum.SUCCESS
                } else {
                    isLoading.value = null
                    responseEnum.value = StatesEnum.ERROR
                }
            } catch (e: Exception) {
                isLoading.value = null
                responseEnum.value = StatesEnum.ERROR
            }
        }
    }
}
