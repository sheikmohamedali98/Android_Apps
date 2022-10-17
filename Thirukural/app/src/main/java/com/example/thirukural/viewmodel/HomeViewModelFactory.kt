package com.example.thirukural.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thirukural.repository.Repository

class HomeViewModelFactory(private val repository: Repository ):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository) as T
        }
        throw  IllegalArgumentException("Model Class Not Found")
    }
}