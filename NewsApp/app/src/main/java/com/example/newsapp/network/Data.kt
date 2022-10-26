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
            author = it.author.toString(),
            content = it.content.toString(),
            date = it.date.toString(),
            id = it.id.toString(),
            imageUrl = it.imageUrl.toString(),
            readMoreUrl = it.readMoreUrl.toString(),
            time = it.time.toString(),
            title = it.title.toString(),
            url = it.url.toString()
        )
    }
}


