package com.example.miniyoutube.api

import com.example.miniyoutube.domain.VideoModel
import com.example.miniyoutube.network.NetworkVideo
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
//https://github.com/sheikmohamedali98/Android_Apps/blob/main/jsonviewer.json
//    https://raw.githubusercontent.com/sheikmohamedali98/Android_Apps/main/jsonviewer.json




private  const val  BASE_URL = "https://raw.githubusercontent.com/sheikmohamedali98/"

private  val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private  val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


interface ApiConnect {
@GET("Android_Apps/main/jsonviewer.json")
suspend fun getVideos():Response<List<NetworkVideo>>
}

object  VideoApi{
    val retrofitService:ApiConnect by lazy {
        retrofit.create(ApiConnect::class.java)
    }
}