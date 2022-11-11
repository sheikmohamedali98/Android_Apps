package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.movieapp.api.MovieApiInstance
import com.example.movieapp.database.MovieDatabasaData
import com.example.movieapp.database.MovieDatabase
import com.example.movieapp.database.asDomineModel
import com.example.movieapp.domin.MovieDomine
import com.example.movieapp.network.MovieResponse
import com.example.movieapp.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class Repository(private val database: MovieDatabase) {
//    suspend fun getMovie(): Response<MovieResponse> {
//        println("\n\n\n\n${MovieApiInstance.retrofitService.getMovie().toString()}\n\n\n\n")
//        return MovieApiInstance.retrofitService.getMovie()
//    }

    val movieList:LiveData<List<MovieDomine>> = Transformations.map(database.roomDao.getMovies()){
        it.asDomineModel()
    }


    suspend fun getMovieData() {
        withContext(Dispatchers.IO) {
            val movieList = MovieApiInstance.retrofitService.getMovie()
            val list = movieList.body()?.results
            if (movieList.isSuccessful) {
                if (list != null) {
                    database.roomDao.inserMovieData(*list.asDatabaseModel().toTypedArray())
                }
            }

        }
    }
        fun searchQuery(string: String):LiveData<List<MovieDomine>> =
            Transformations.map(database.roomDao.searchMovieList(string)){
                it.asDomineModel()
            }







}