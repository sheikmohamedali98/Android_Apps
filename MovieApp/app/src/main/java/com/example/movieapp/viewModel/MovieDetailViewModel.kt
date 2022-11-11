package com.example.movieapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.domin.MovieDomine

class MovieDetailViewModel(private val movieDomine: MovieDomine):ViewModel() {
    private val _selectedMovie =MutableLiveData<MovieDomine>()
    val selectMovie:LiveData<MovieDomine>
    get() = _selectedMovie

    init {

        _selectedMovie.value = movieDomine
    }
}