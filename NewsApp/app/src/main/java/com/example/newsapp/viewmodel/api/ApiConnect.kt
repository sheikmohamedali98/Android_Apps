package com.example.newsapp.viewmodel.api

import com.example.newsapp.model.domain.Data
import com.example.newsapp.model.domain.NewsResponse
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

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private  val  retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface  ApiConnect{
@GET("news")
suspend fun getAllNews(
    @Query("category") category:String = "all"):Response<NewsResponse>

}
object NewsApi{
    val retrofitService: ApiConnect by lazy {
        retrofit.create(ApiConnect::class.java)
    }
}