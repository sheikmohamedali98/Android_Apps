package com.example.movieapp.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.database.MovieDatabase
import com.example.movieapp.database.getInstace
import com.example.movieapp.domin.MovieDomine
import com.example.movieapp.network.MovieResponse
import com.example.movieapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieListViewModel(application: Application) : AndroidViewModel(application) {


    private val _navigateToSelectedMovies = MutableLiveData<MovieDomine?>()

    val navigateToSelectedMovies: LiveData<MovieDomine?>
        get() = _navigateToSelectedMovies

    val database = getInstace(application)
    val repository = Repository(database)
    val listOfMovie = repository.movieList

    init {


    }

    fun getMovieFromRepository() {
        viewModelScope.launch {
            repository.getMovieData()
        }
    }


    fun searchQuery(string: String): LiveData<List<MovieDomine>> {
        return repository.searchQuery(string)
    }

    fun displayData(movieDomine: MovieDomine) {
        _navigateToSelectedMovies.value = movieDomine
    }

    fun displayProperties() {
        _navigateToSelectedMovies.value = null
    }




}