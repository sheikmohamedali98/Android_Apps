package com.example.miniyoutube.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.miniyoutube.api.VideoApi
import com.example.miniyoutube.database.getInstance
import com.example.miniyoutube.domain.VideoModel
import com.example.miniyoutube.repository.VideoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
//    private val _response = MutableLiveData<List<VideoModel>>()
//
//    val response: LiveData<List<VideoModel>>
//        get() = _response
////
//    private val _status = MutableLiveData<String>()
//
//    val status: LiveData<String>
//        get() = _status
//
//    private val viewModelJob = Job()
//    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _latitude = MutableLiveData<String>()
    val latitude: LiveData<String>
        get() = _latitude

    private val _longtitude = MutableLiveData<String>()
    val longtitude: LiveData<String>
        get() = _longtitude

    private val database = getInstance(application)
    private val videoRepository = VideoRepository(database)
    val playList = videoRepository.videoList

    init {
        viewModelScope.launch {
            videoRepository.refreshVideo()
        }
    }

//    fun getlist(){
//        println("\n\n\n\n in ViewModel ${playList.toString()}\n\n\n\n" )
//    }


//
//    private fun getVideoProperties() {
//
//        coroutineScope.launch {
//            val getPropertiesDiffered = VideoApi.retrofitService.getVideos()
//            try {
//                val listResult = getPropertiesDiffered
////                _response.value = "Sucess ${listResult.size}"
//                if(listResult.isSuccessful){
//                    _response.value = listResult.body()
//                }
//            }catch (t:Throwable){
//
//            }
//
//        }
//
//    }

//    override fun onCleared() {
//        super.onCleared()
//        viewModelJob.cancel()
//    }

    fun displayPhoto(it: VideoModel) {

    }

}