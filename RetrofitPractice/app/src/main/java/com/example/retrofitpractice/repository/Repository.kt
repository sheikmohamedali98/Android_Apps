package com.example.retrofitpractice.repository

import com.example.retrofitpractice.api.RetrofitInstance
import com.example.retrofitpractice.model.Post
import retrofit2.Response

class Repository {
    suspend fun getPost():Response<Post> {
        return  RetrofitInstance.api.getPost()
    }
    suspend fun getPost2(number: Int):Response<Post> {
        return  RetrofitInstance.api.getPost2(number)
    }
    suspend fun  getCustomePost(userId:Int):Response<List<Post>>{
        return RetrofitInstance.api.getCustomePost(userId)
    }
    suspend fun  getCustomePost2(userId:Int,sort:String,order:String):Response<List<Post>>{
        return RetrofitInstance.api.getCustomePost2(userId,sort,order)
    }
    suspend fun  getCustomePost3(userId:Int,option:Map<String,String>):Response<List<Post>>{
        return RetrofitInstance.api.getCustomePost3(userId,option)
    }
}