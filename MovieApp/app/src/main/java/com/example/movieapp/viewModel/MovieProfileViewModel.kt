package com.example.movieapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieProfileViewModel:ViewModel() {

    private val _imagerUri = MutableLiveData<String>()
    val imageUri:LiveData<String>
    get() = _imagerUri

}