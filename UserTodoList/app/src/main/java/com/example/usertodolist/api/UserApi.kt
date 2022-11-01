package com.example.usertodolist.api

import com.example.usertodolist.domine.UserResponse
import com.example.usertodolist.networkdata.UserNerWorkdata
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private  val BASE_URL1 = "https://gorest.co.in/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL1).build()

interface UserApi {
    @GET("public/v2/users")
    suspend fun getUserDetails(): Response<List<UserNerWorkdata>>

}

object UserApiConnect {
    val retrofitService: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }
}