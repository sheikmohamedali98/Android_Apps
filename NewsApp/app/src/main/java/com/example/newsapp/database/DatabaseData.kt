package com.example.newsapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.domain.DomainData

@Entity(tableName = "news_database")
data class DatabaseData(
    val author: String,
    val content: String,
    val date: String,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val imageUrl: String,
    val readMoreUrl: String,
    val time: String,
    val title: String,
    val url: String
)
//{
//    @PrimaryKey(autoGenerate = true)
//    var newsId:Int = 0
//}


fun List<DatabaseData>.asDomainModel(): List<DomainData> {
    return map {
        DomainData(
            author = it.author,
            content = it.content,
            date = it.date,
            id = it.id,
            imageUrl = it.imageUrl,
            readMoreUrl = it.readMoreUrl,
            time = it.time,
            title = it.title,
            url = it.url
        )
    }
}