package com.example.newsapp.network

import com.example.newsapp.database.DatabaseData


data class Data(
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

// map all data to database data
fun List<Data>.asDatabaseModel(): List<DatabaseData> {
    return map {
        DatabaseData(
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