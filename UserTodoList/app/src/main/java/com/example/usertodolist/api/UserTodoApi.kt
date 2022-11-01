package com.example.usertodolist.api

import com.example.usertodolist.networkdata.UserToDoListNetWorkData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private  val BASE_URL = "https://gorest.co.in/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL).build()


interface UserTodoApi {
    @GET("public/v2/todos")
    suspend fun getTodoList():Response<List<UserToDoListNetWorkData>>
}

object UserTodoApiConnect {
    val retrofitService: UserTodoApi by lazy {
        retrofit.create(UserTodoApi::class.java)
    }
}