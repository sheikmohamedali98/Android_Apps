package com.example.movieapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.domin.MovieDomine

@Entity(tableName = "Movie")
data class MovieDatabasaData(
    val createdAt: String, // 2022-01-19T23:54:45.509Z
    val description: String, // A menos de dos semanas de casarse con Gretchen (Kaley Cuoco), la chica de sus sueños, Doug Harris (Josh Gad) no encuentra siquiera a un conocido que pueda hacer de padrino en su boda, por lo que termina contratando a Jimmy Callahan (Kevin Hart), propietario de una empresa que provee de falsos padrinos a novios solitarios como él..
    @PrimaryKey(autoGenerate = false)
    val id: String, // 61e8a4c5f2eb8ced20633f4f
    val image: String, // https://image.tmdb.org/t/p/w300/c0p6YAhrl5tLNqXydVUotJiEufR.jpg
    val rating: String, // 6.4/10
    val release: String, // 16 Jan 2015
    val title: String, // El gurú de las bodas
    val titleOriginal: String, // The Wedding Ringer
    val updatedAt: String, // 2022-06-28T19:34:59.870Z
    val uuid: String, // el-guru-de-las-bodas
    val year: String,
)

fun List<MovieDatabasaData>.asDomineModel():List<MovieDomine>{
    return  map {
        MovieDomine(
            createdAt = it.createdAt,
            description = it.description,
            id =  it.id,
            image = it.image,
            rating = it.rating,
            release =  it.release,
            title = it.title,
            titleOriginal = it.titleOriginal,
            updatedAt =  it.updatedAt,
            uuid = it.uuid,
            year = it.year
        )
    }
}
