package com.example.movieapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.domin.MovieDomine

class MovieDetailViewmodelFactory(private val movieDomine: MovieDomine) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(movieDomine) as T
        }
        throw  IllegalArgumentException("factoy error")
    }
}