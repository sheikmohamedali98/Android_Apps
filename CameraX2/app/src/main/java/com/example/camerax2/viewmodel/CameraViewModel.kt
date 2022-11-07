package com.example.camerax2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraViewModel:ViewModel() {
    private val _preview= MutableLiveData<String>()
    val preview:LiveData<String>
    get() = _preview

    init {
        _preview.value = ""
    }

}