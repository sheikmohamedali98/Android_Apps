package com.example.retrofitpractice.api

import com.example.retrofitpractice.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import java.util.SplittableRandom

interface SampleApi {
    @GET("posts/1")
    suspend fun getPost():Response<Post>

    @GET("posts/{postNumber}")
    suspend fun getPost2(
        @Path("postNumber")number: Int
    ):Response<Post>
//    @GET("posts/{postNumber}")
//    suspend fun getPost2(
//    @Path("postNumber")):Response<Post>


    //posts?userId=3
    @GET("posts")
    suspend fun getCustomePost(
        @Query("userId") userId:Int
    ):Response<List<Post>>

    @GET("posts")
    suspend fun getCustomePost2(
        @Query("userId") userId:Int,
        @Query("_sort") sort:String,
        @Query("_order") order:String

    ):Response<List<Post>>


    @GET("posts")
    suspend fun getCustomePost3(
        @Query("userId")userId:Int,
    @QueryMap operation:Map<String,String>

    ):Response<List<Post>>
}