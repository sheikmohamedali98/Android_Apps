package com.example.miniyoutube.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.miniyoutube.domain.VideoModel

class VideoViewModelFactory(
    private val videoModel: VideoModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoViewModel::class.java)) {
            return VideoViewModel(videoModel) as T
        }
        throw IllegalArgumentException("model class cannot creat")
    }
}