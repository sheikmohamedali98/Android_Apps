package com.example.miniyoutube.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.miniyoutube.api.VideoApi
import com.example.miniyoutube.asDomainModel
import com.example.miniyoutube.database.VideoDatabase
import com.example.miniyoutube.domain.VideoModel
import com.example.miniyoutube.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoRepository(private val database: VideoDatabase) {

    val videoList: LiveData<List<VideoModel>> = Transformations.map(database.roomDao.getVideo()){
       it.asDomainModel()
    }



    suspend fun refreshVideo(){
        withContext(Dispatchers.IO){

           val playlist = VideoApi.retrofitService.getVideos()
            val list = playlist.body()

            if(playlist.isSuccessful) {
                println("\n\n\n\n in repository playlist ${list.toString()}\n\n\n\n" )

                println("\n\n\n\n in repository ${videoList.value.toString()}\n\n\n\n" )
                list?.asDatabaseModel()?.let { database.roomDao.insertAll(*it) }
            }else{
                println("\n\n\n${playlist.code()}\n\n\n")
            }
        }
    }
}