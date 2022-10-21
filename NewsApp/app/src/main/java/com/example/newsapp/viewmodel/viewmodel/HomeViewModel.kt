package com.example.newsapp.viewmodel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.domain.Data
import com.example.newsapp.model.domain.NewsResponse
import com.example.newsapp.viewmodel.api.NewsApi
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel:ViewModel() {

    private val _responce = MutableLiveData<Response<NewsResponse>>()

    val response:LiveData<Response<NewsResponse>>
    get() = _responce



    init {

        getNewsProperties()
    }

    private fun getNewsProperties() {
        viewModelScope.launch() {
            val getNewsDiffered = NewsApi.retrofitService.getAllNews()
            try{
            val listResult =getNewsDiffered

                _responce.value = listResult
            }catch (t:Throwable){

            }

        }
    }
}