package com.example.movieapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserMovieData(vararg movieDatabasaData: MovieDatabasaData)

    @Query("SELECT * FROM Movie" )
    fun getMovies():LiveData<List<MovieDatabasaData>>

    @Query("SELECT * FROM Movie WHERE title Like :searchQuery")
    fun searchMovieList(searchQuery:String):LiveData<List<MovieDatabasaData>>
}

