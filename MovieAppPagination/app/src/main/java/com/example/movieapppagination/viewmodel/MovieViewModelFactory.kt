package com.example.movieapppagination.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapppagination.api.MovieNetWork

class MovieViewModelFactory(private  val api:MovieNetWork):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieListViewModel::class.java)){
            return MovieListViewModel(api) as T
        }
        throw IllegalArgumentException("Model cannot create")
    }

}