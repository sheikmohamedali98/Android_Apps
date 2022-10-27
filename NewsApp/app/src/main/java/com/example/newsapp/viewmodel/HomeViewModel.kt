package com.example.newsapp.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.api.NewsApi
import com.example.newsapp.api.NewsFilter
import com.example.newsapp.api.WeatherApi
import com.example.newsapp.database.getInstance
import com.example.newsapp.domain.DomainData
import com.example.newsapp.domain.weather.Current
import com.example.newsapp.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application):AndroidViewModel(application) {

//    private val _newsProperties = MutableLiveData<List<Data>?>()
//
//    val newsProperties: MutableLiveData<List<Data>?>
//    get() = _newsProperties
//
//
//
//    init {
//        getNewsProperties("all")
//
//    }
//
//     fun getNewsProperties(filter:String) {
//        viewModelScope.launch() {
////            println("\n\n\n\n${filter.value}\n\n\n\n")
//            val getNewsResponse = NewsApi.retrofitService.getAllNews(filter)
//            if(getNewsResponse.isSuccessful){
//                val item =  getNewsResponse.body()?.data
//               _newsProperties.value = item
////                println("\n\n\n\n\n${_newsProperties.value}\n\n\n\n\n")
//            }else{
//
//            }
//
//        }
//    }


    private val _weatherProperties = MutableLiveData<String?>()

    val weatherProperties: MutableLiveData<String?>
    get() = _weatherProperties

    private val _navigateToSelectedNews = MutableLiveData<DomainData?>()

    val navigateToSelectedNews:LiveData<DomainData?>
    get() = _navigateToSelectedNews

    private val database = getInstance(application)
    private  val  newsRepository = NewsRepository(database)

    init {

        getProperties(NewsFilter.ALL)
//        getWeather()
    }

    fun getProperties(filter: NewsFilter){
        viewModelScope.launch {
            newsRepository.refreshNews(filter.value)
        }
    }
    val listOfNews = newsRepository.newsList

//    filter: String

    fun getWeather(){
        viewModelScope.launch(Dispatchers.IO) {
            val getResponse = WeatherApi.weatherService.getWeather()
            if(getResponse.isSuccessful){
                val item = getResponse.body()?.current?:"NULL"
                _weatherProperties.value = item.toString()
            }
            else{
                _weatherProperties.value = "cannot proifghjk"
            }
        }
    }

    fun displayData(domainData: DomainData){
        _navigateToSelectedNews.value = domainData
    }

    fun displayProperties(){
        _navigateToSelectedNews.value = null
    }
     fun updateFilter(filter: NewsFilter) {
       getProperties(filter)
        println("\n\n\n\n${listOfNews}\n\n\n")
    }
}