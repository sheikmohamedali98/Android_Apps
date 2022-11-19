package com.example.newsapp.network

import com.example.newsapp.network.weather_data.Weather
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object WeatherApiClient {
    /**
     * base url
     * https://api.weatherbit.io/v2.0/current?lat=12.03093&lon=32.32423&key=532e61560fc8468bb9fda40e97b4856c
     */
    private val BASE_URL = "https://api.weatherbit.io/v2.0/"

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    /**
     * creating the instance of the retrofit as lazy
     */
    private val retrofit: Retrofit by lazy{
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    /**
     * creating the object of the ApiService as lazy
     */
    val weatherApiService:WeatherApiService by lazy{
        retrofit.create(WeatherApiService::class.java)
    }
}

/**
 * Below object class lets create an interface to define how Retrofit talks to the service using the GetMethod
 */
interface WeatherApiService{
    /**
     * @fetchALL is the data fetching method that is annotated with
     * @GET annotation passing the launches as path
     */
    @GET("current")
  suspend  fun fetchAll(
        @Query("lat") lat:String,
        @Query("lon")lon:String,
        @Query("key")key:String="532e61560fc8468bb9fda40e97b4856c"
    ): Response<Weather>
}