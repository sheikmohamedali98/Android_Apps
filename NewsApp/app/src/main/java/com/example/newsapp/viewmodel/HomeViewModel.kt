package com.example.newsapp.viewmodel


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.api.NewsFilter
import com.example.newsapp.database.getInstance
import com.example.newsapp.domain.DomainData
import com.example.newsapp.domain.weather.WeatherResponse
import com.example.newsapp.repository.NewsRepository
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

    private  val _myResponse = MutableLiveData<WeatherResponse>()

    val myResponse:LiveData<WeatherResponse>
    get() = _myResponse


    private val _navigateToSelectedNews = MutableLiveData<DomainData?>()

    val navigateToSelectedNews:LiveData<DomainData?>
    get() = _navigateToSelectedNews

    private val database = getInstance(application)
    private  val  newsRepository = NewsRepository(database)


            init {

        getProperties(NewsFilter.ALL)
//        getWeather()
//        getWeatherPropertiessss()
    }

    fun getProperties(filter: NewsFilter){
        viewModelScope.launch {
            try {
                newsRepository.refreshNews(filter.value)
            }catch (t:Throwable){

            }
        }
    }
    val listOfNews = newsRepository.newsList

//    fun getWeatherPropertiessss(){
//        viewModelScope.launch {
//            try {
//                newsRepository.getWeather("chennai")
//            }catch (t:Throwable){
//
//            }
//        }
//    }

    fun getWeather(cityName: String){

        viewModelScope.launch {
            val response = newsRepository.getWeatherList(cityName)
            _myResponse.value = response
            Log.i("TAG", _myResponse.value!!.location.name.toString())
        }
    }

//    filter: String

//    fun getWeather1(){
//        viewModelScope.launch(Dispatchers.IO) {
//            val getResponse = WeatherApi.weatherService.getWeather()
//            if(getResponse.isSuccessful){
//                val item = getResponse.body()?.current?:"NULL"
//                _weatherProperties.value = item
//            }
//            else{
//                _weatherProperties.value = "cannot proifghjk"
//            }
//        }
//    }

//    getWeather(){
//        viewModelScope.launch {
//
//        }
//    }

//    fun getWeather(location: Location) {
//        viewModelScope.launch {
//            strangerRepository.getWeatherDetailsFromNetworkForHome(location.latitude, location.longitude, API_WEATHER_KEY_2)
//        }
//    }

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