package com.example.thirukural.api

import com.example.thirukural.util.Constance.Companion.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
object RetrofitInstance {

   private val retrofit by lazy {
       Retrofit.Builder().baseUrl(BASE_URL)
           .addConverterFactory(MoshiConverterFactory.create(moshi))
           .addCallAdapterFactory(CoroutineCallAdapterFactory())
           .build()
   }

    val  api:ApiConnect by lazy {
        retrofit.create(ApiConnect::class.java)
    }

}