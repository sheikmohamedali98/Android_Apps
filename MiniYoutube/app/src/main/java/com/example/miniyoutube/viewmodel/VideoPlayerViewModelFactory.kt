package com.example.miniyoutube.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VideoPlayerViewModelFactory(private val videoUrl:String):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(VideoPlayerViewModel::class.java)){
            return VideoPlayerViewModel(videoUrl) as T
        }
        return super.create(modelClass)
    }

}