package com.example.newsapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.newsapp.api.NewsApi
import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.database.NewsDataBase
import com.example.newsapp.database.asDomainModel
import com.example.newsapp.domain.DomainData
import com.example.newsapp.domain.weather.WeatherResponse
import com.example.newsapp.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(private  val database:NewsDataBase) {


    val newsList:LiveData<List<DomainData>> = Transformations.map(database.dataDao.getNews()){
        it.asDomainModel()
    }
//     val weather = MutableLiveData<WeatherResponse?>()

//    fun checkPrint(){
//        println("\n\n\n print neew List iNNreReposi ${newsList}\n\n\n")
//    }

    suspend fun refreshNews(filter:String){
        withContext(Dispatchers.IO){
            val newsList = NewsApi.retrofitService.getAllNews(filter)
            val list = newsList.body()?.data

            if(newsList.isSuccessful){
                if (list != null) {
                    println("\n\n\n\n${newsList.body()?.data}\n\n\n\n")
                    database.dataDao.insertNews(*list.asDatabaseModel().toTypedArray())
                }
            }

        }
    }
//
//   suspend fun getWeather(cityname:String){
//        withContext(Dispatchers.IO){
//            try{
//                val weatherList = WeatherApi.weatherService.getWeather()
//                val list = weatherList.body()
//                if(weatherList.isSuccessful){
//                    weather.postValue(list)
//                } else {
//
//                }
//            }catch (e:Exception){
//                Log.i("TAG",e.message.toString())
//            }
//
//        }
//    }

    suspend fun getWeatherList(cityName:String):WeatherResponse{
   return RetrofitInstance.api.getWeather(q = cityName)

    }

}

