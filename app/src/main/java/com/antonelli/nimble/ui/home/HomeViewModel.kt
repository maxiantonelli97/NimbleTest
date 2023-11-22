package com.antonelli.nimble.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonelli.nimble.entity.SurveysResponseModel
import com.antonelli.nimble.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    private val _surveys = MutableLiveData<ArrayList<SurveysResponseModel>>()
    val surveys: LiveData<ArrayList<SurveysResponseModel>> = _surveys

    fun getSurveys() {
        viewModelScope.launch {
            val params = hashMapOf<String, Any>()
            params["page"] = 1
            params["page_size"] = 5
            val response = repository.getSurveys(params, "Bearer 123")
            if (response.body()?.data != null) {
                _surveys.value = response.body()!!.data
            }
        }
    }
}
