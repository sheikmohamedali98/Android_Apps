package com.example.movieapp.network


import com.example.movieapp.database.MovieDatabasaData
import com.example.movieapp.domin.MovieDomine
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    @Json(name = "messageStatus")
    val messageStatus: String, // OK
    @Json(name = "results")
    val results: List<Result>,
    @Json(name = "status")
    val status: Int, // 200
    @Json(name = "success")
    val success: Boolean, // true
    @Json(name = "total_pages")
    val totalPages: Int, // 348
    @Json(name = "total_results")
    val totalResults: Int, // 8351
) {
    @JsonClass(generateAdapter = true)
    data class Result(
        @Json(name = "actors")
        val actors: List<Any>,
        @Json(name = "countries")
        val countries: List<Country>,
        @Json(name = "createdAt")
        val createdAt: String, // 2022-01-19T23:54:45.509Z
        @Json(name = "description")
        val description: String, // A menos de dos semanas de casarse con Gretchen (Kaley Cuoco), la chica de sus sueños, Doug Harris (Josh Gad) no encuentra siquiera a un conocido que pueda hacer de padrino en su boda, por lo que termina contratando a Jimmy Callahan (Kevin Hart), propietario de una empresa que provee de falsos padrinos a novios solitarios como él..
        @Json(name = "directors")
        val directors: List<Any>,
        @Json(name = "embedUrls")
        val embedUrls: List<EmbedUrl>,
        @Json(name = "episodes")
        val episodes: List<Any>,
        @Json(name = "escritors")
        val escritors: List<Any>,
        @Json(name = "genres")
        val genres: List<Genre>,
        @Json(name = "_id")
        val id: String, // 61e8a4c5f2eb8ced20633f4f
        @Json(name = "image")
        val image: String, // https://image.tmdb.org/t/p/w300/c0p6YAhrl5tLNqXydVUotJiEufR.jpg
        @Json(name = "index")
        val index: Int, // 40
        @Json(name = "otherTitles")
        val otherTitles: List<Any>,
        @Json(name = "rating")
        val rating: String, // 6.4/10
        @Json(name = "release")
        val release: String, // 16 Jan 2015
        @Json(name = "title")
        val title: String, // El gurú de las bodas
        @Json(name = "titleOriginal")
        val titleOriginal: String, // The Wedding Ringer
        @Json(name = "updatedAt")
        val updatedAt: String, // 2022-06-28T19:34:59.870Z
        @Json(name = "uuid")
        val uuid: String, // el-guru-de-las-bodas
        @Json(name = "year")
        val year: String, // 2015
    ) {
        @JsonClass(generateAdapter = true)
        data class Country(
            @Json(name = "name")
            val name: String, // USA
            @Json(name = "uuid")
            val uuid: String, // usa
        )

        @JsonClass(generateAdapter = true)
        data class EmbedUrl(
            @Json(name = "priority")
            val priority: Int, // 4
            @Json(name = "server")
            val server: String, // fembed
            @Json(name = "url")
            val url: String, // https://dutrag.com/v/1ezegcjmn787gd6
        )

        @JsonClass(generateAdapter = true)
        data class Genre(
            @Json(name = "name")
            val name: String, // Comedia
            @Json(name = "uuid")
            val uuid: String, // comedia
        )
    }


}

fun List<MovieResponse.Result>.asDatabaseModel(): List<MovieDatabasaData> {

    return map {
        MovieDatabasaData(
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