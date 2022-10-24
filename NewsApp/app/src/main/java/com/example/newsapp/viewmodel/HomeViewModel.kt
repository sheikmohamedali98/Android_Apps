package com.example.newsapp.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.network.Data
import com.example.newsapp.api.NewsApi
import kotlinx.coroutines.launch

class HomeViewModel:ViewModel() {

    private val _newsProperties = MutableLiveData<List<Data>?>()

    val newsProperties: MutableLiveData<List<Data>?>
    get() = _newsProperties



    init {
        getNewsProperties("all")

    }

     fun getNewsProperties(filter:String) {
        viewModelScope.launch() {
//            println("\n\n\n\n${filter.value}\n\n\n\n")
            val getNewsResponse = NewsApi.retrofitService.getAllNews(filter)
            if(getNewsResponse.isSuccessful){
                val item =  getNewsResponse.body()?.data
               _newsProperties.value = item
//                println("\n\n\n\n\n${_newsProperties.value}\n\n\n\n\n")
            }else{

            }

        }
    }

    fun updateFilter(filter: String){
        getNewsProperties("all")
    }
}