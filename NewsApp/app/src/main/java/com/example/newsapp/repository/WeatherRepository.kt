package com.example.newsapp.repository

import com.example.newsapp.network.WeatherApiClient
import com.example.newsapp.network.weather_data.Weather
import retrofit2.Call
import retrofit2.Response
import retrofit2.await


class WeatherRepository {
    suspend fun getWeatherData(lat:String, lon:String): Response<Weather> {
        return WeatherApiClient.weatherApiService.fetchAll(lat, lon)
        println("\n\n\n\n${WeatherApiClient.weatherApiService.fetchAll("12", "80").body().toString()}\n\n\n\n")
    }
}