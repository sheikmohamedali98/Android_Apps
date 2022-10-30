package com.example.miniyoutube.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.miniyoutube.domain.VideoModel

class VideoViewModel( videoModel: VideoModel,application: Application): AndroidViewModel(application) {

    private val _videoProperty = MutableLiveData<VideoModel>()

    val videoProperty:LiveData<VideoModel>
    get() = _videoProperty

    init {
        _videoProperty.value = videoModel
    }

}