package com.example.miniyoutube

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miniyoutube.domain.VideoModel

@Entity
data class DatabaseVideo(
    @PrimaryKey
    val id:Int,
    val name:String,
    val tn_img:String,
    val video_url:String
)


fun List<DatabaseVideo>.asDomainModel():List<VideoModel>{
    return map{
        VideoModel(
            id = it.id,
            name = it.name,
            tn_img = it.tn_img,
            video_url = it.video_url
        )
    }
}
//fun List<DatabaseVideo>.asDomainModel(): List<Video> {
//    return map {
//        Video(
//            url = it.url,
//            title = it.title,
//            description = it.description,
//            updated = it.updated,
//            thumbnail = it.thumbnail
//        )
//    }

