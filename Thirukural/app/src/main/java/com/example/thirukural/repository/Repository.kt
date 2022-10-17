package com.example.thirukural.repository

import com.example.thirukural.api.RetrofitInstance
import com.example.thirukural.model.Thirukural
import retrofit2.Response

class Repository {

    suspend fun getKurals(number:Int):Response<Thirukural>{
        return  RetrofitInstance.api.getKural(number)
    }
}