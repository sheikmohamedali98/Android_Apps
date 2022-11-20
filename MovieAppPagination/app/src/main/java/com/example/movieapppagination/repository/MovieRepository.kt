package com.example.movieapppagination.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.movieapppagination.api.MovieNetWork
import com.example.movieapppagination.domin.MovieDomin
import com.example.movieapppagination.network.MovieResponse
import com.example.movieapppagination.pagination.MoviePagingSource
import retrofit2.Response

class MovieRepository {


//fun movieDetails(): LiveData<PagingData<MovieDomin>> {
//     return  Pager(
//          config =  PagingConfig(
//               enablePlaceholders = false,
//               initialLoadSize = 10
//          ),
//          pagingSourceFactory = {
//               MoviePagingSource(MovieApiService)
//          }
//     ).liveData
//}
//suspend fun getMoviesList():Response<MovieResponse>{
//    return  MovieApiService.retrifitService.getMovieFromNetWork()
//}
}