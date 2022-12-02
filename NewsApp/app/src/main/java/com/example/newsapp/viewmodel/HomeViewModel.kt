package com.example.newsapp.viewmodel
import retrofit2.Callback
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.api.NewsFilter
import com.example.newsapp.database.DatabaseData
import com.example.newsapp.database.getInstance
import com.example.newsapp.domain.DomainData
import com.example.newsapp.network.weather_data.Weather
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class HomeViewModel(application: Application):AndroidViewModel(application) {




    private  val _myResponse = MutableLiveData<Response<Weather>>()
    val myResponse:LiveData<Response<Weather>>
    get() = _myResponse

    private val _temperature = MutableLiveData<String>()
    val temperature:LiveData<String>
    get() = _temperature


    private val _navigateToSelectedNews = MutableLiveData<DomainData?>()

    val navigateToSelectedNews:LiveData<DomainData?>
    get() = _navigateToSelectedNews

    private val database = getInstance(application)
    private  val  newsRepository = NewsRepository(database)

    private val weatherRepository = WeatherRepository()

            init {

        getProperties(NewsFilter.ALL)
//        getWeather()
//        getWeatherPropertiessss()
    }
//
    fun getProperties(filter: NewsFilter){
        viewModelScope.launch {
            try {
                newsRepository.refreshNews(filter.value)
//                val response = newsRepository.getWeatherList("chennai")
//                println("\n\n\n${response.body().toString()}\n\n\n")
            }catch (t:Throwable){

            }
        }
    }
    val listOfNews = newsRepository.newsList

    fun getWeatherDetails(lat:String, lon:String){
        viewModelScope.launch {
            val response = weatherRepository.getWeatherData(lat,lon)
            _myResponse.value = response
        }

    }

//    fun getWeatherPropertiessss(){
//        viewModelScope.launch {
//            try {
//                newsRepository.getWeather("chennai")
//            }catch (t:Throwable){
//
//            }
//        }
//    }
//    fun getWeather(cityName: String){
//
//        viewModelScope.launch {
//            val response = newsRepository.getWeatherList(cityName)
//            _myResponse.value = response.body()
//            _temperature.value = response.body()?.current?.tempC.toString()
//            Log.i("TAG", _myResponse.value!!.location.name.toString())
//        }
//    }

//    filter: String

//    fun getWeather1(){
//        viewModelScope.launch(Dispatchers.IO) {
//            val getResponse = RetrofitInstance.api.getWeather()
//            if(getResponse.i){
//                val item = getResponse.body()?.current?:"NULL"
//                _weatherProperties.value = item
//            }
//            else{
//                _weatherProperties.value = "cannot proifghjk"
//            }
//        }
//    }

    fun searchDatabase(searchQuery:String):LiveData<List<DatabaseData>>{
        return    newsRepository.searchDatabase(searchQuery)

    }

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
    }
}