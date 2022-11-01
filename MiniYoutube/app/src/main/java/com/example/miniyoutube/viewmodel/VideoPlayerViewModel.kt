package com.example.miniyoutube.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VideoPlayerViewModel (videoUrl:String) : ViewModel(){
    private val _videoId = MutableLiveData<String>()
    val videoId:LiveData<String>
    get() = _videoId

    init{
        _videoId.value = videoUrl
    }
}