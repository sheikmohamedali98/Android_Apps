package com.example.newsapp.model.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_data")
data class Data(
    @PrimaryKey(autoGenerate = true)
    val newsId:Int? = null,
    val author: String?,
    val content: String?,
    val date: String?,
    val id: String?,
    val imageUrl: String?,
    val readMoreUrl: String?,
    val time: String?,
    val title: String?,
    val url: String?
)