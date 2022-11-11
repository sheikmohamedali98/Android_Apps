package com.example.movieapp.api

import com.example.movieapp.network.MovieResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers


//https://movies-app1.p.rapidapi.com/api/movies
/**
 * header
 * X-RapidAPI-Host    movies-app1.p.rapidapi.com
 * "X-RapidAPI-Key     bd21a26e24msh7d7fd41771f434ap1361a7jsn548b073f8a28"

 */

private const val BASE_URL_NEWS = "https://newsapi.org/"
private  const val  BASE_URL = "https://movies-app1.p.rapidapi.com/"
val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

val retrofit  = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
    BASE_URL).build()
//https://newsapi.org/v2/everything?q=movies&apiKey=079dac74a5f94ebdb990ecf61c8854b7&pageSize=20&page=5
//https://api.themoviedb.org/3/movie/popular?api_key=417e9d9933e746049643f91595ac53d7
interface MovieApi {
    @Headers(
       "X-RapidAPI-Host:movies-app1.p.rapidapi.com",
        "X-RapidAPI-Key:bd21a26e24msh7d7fd41771f434ap1361a7jsn548b073f8a28"
    )
    @GET("api/movies")
    suspend fun getMovie():Response<MovieResponse>
}

object MovieApiInstance{
    val retrofitService:MovieApi by lazy {
         retrofit.create(MovieApi::class.java)
    }
}