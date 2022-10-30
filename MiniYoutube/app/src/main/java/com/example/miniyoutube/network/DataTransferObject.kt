package com.example.miniyoutube.network

import com.example.miniyoutube.DatabaseVideo

//@JsonClass(generateAdapter = true)
//data class NetworkVideoContainer(val videoModel: List<NetworkVideo>)

data class NetworkVideo (
    val id:Int,
    val name:String,
    val tn_img:String,
    val video_url:String
    )

fun List<NetworkVideo>.asDatabaseModel(): Array<DatabaseVideo>{
    return  map{
        DatabaseVideo(
            id = it.id,
            name = it.name,
            tn_img = it.tn_img,
            video_url = it.video_url
        )
    }.toTypedArray()
}