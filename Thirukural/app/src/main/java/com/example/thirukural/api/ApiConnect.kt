package com.example.thirukural.api

import com.example.thirukural.model.Thirukural
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


//api?num=x
interface ApiConnect {

    @GET("api")
    suspend fun getKural(
        @Query("num") number:Int
    ): Response<Thirukural>
}