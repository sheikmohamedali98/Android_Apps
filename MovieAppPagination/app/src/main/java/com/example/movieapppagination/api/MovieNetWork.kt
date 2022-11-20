package com.example.movieapppagination.api

import com.example.movieapppagination.network.MovieResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://api.themoviedb.org/3/movie/top_rated?api_key=417e9d9933e746049643f91595ac53d7&language=en-US&page=1
//https://api.themoviedb.org/https://api.themoviedb.org/?api_key=417e9d9933e746049643f91595ac53d7&language=en-US&page=1
//https://image.tmdb.org/t/p/w200/od22ftNnyag0TTxcnJhlsu3aLoU.jpg
private const val BASE_URL = "https://api.themoviedb.org/"




interface MovieNetWork {

    @GET("3/movie/top_rated")
suspend fun getMovieFromNetWork(
        @Query("api_key") api_key: String = "417e9d9933e746049643f91595ac53d7",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
    ):Response<MovieResponse>


companion object{
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    fun getApi() = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build().create(MovieNetWork::class.java)

}
}


//object MovieApiService{
//    val retrifitService:MovieNetWork by lazy {
//        retrofit.create(MovieNetWork::class.java)
//    }
//}