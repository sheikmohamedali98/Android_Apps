package com.example.newsapp.api

import com.example.newsapp.domain.weather.Current
import com.example.newsapp.domain.weather.WeatherResponse
import com.example.newsapp.network.NewsResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//      news?category=business
//      https://inshorts.deta.dev/news?category=business
private  const val BASE_URL = "https://inshorts.deta.dev/"
private  const val WEATHER_URL = "http://api.weatherapi.com/v1/"
// weather Api
//https://fcc-weather-api.glitch.me/api/current?lat=13&lon=80
//https://api.weatherbit.io/v2.0/current?lat=13&lon=80&key=030314b750cc43e7b39e503dfe37150c

//geek with forcast
//http://api.weatherapi.com/v1/forecast.json?key=fd71c6568370478eb68102009222510&q=London&days=1&aqi=yes&alerts=yes


//normal Current
//http://api.weatherapi.com/v1/current.json?key=fd71c6568370478eb68102009222510&q=London&aqi=yes

enum class NewsFilter(val value:String){
    ALL("all"),SPORT("sports"),BUSINESS("business"),TECHNOLOGY("technology"),AUTOMOBILE("automobile")
}
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private  val  retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


private val weatherRetrofit= Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(WEATHER_URL)
    .build()

//private  val fusedLocation = LocationServices.getFusedLocationProviderClient

interface  ApiConnect {
    @GET("news")
    suspend fun getAllNews(@Query("category") category: String): Response<NewsResponse>

//@GET("current")
//suspend fun getWeather(
//    @Query("lat")latitude:Double ,
//    @Query("lon")longitude:Double
//):Response<WeatherResponse>
}

interface WeatherApiConnect{

@GET("forecast.json")
suspend fun getWeather(
    @Query("key") key:String ="fd71c6568370478eb68102009222510",
    @Query("q") q:String="London",
    @Query("days")days:String = "1",
    @Query("api")api:String="yes",
    @Query("alerts")alerts:String="yes"
):Response<WeatherResponse>

}
object NewsApi{
    val retrofitService: ApiConnect by lazy {
        retrofit.create(ApiConnect::class.java)
    }


}

object WeatherApi{
    val weatherService:WeatherApiConnect by lazy {
        weatherRetrofit.create(WeatherApiConnect::class.java)
    }
}