package com.example.movieapppagination.domin

import com.example.movieapppagination.network.MovieResponse
import com.squareup.moshi.Json

data class MovieDomin(
    val adult: Boolean, // false
    val backdropPath: String, // /rl7Jw8PjhSIjArOlDNv0JQPL1ZV.jpg
    val id: Int, // 851644
    val originalLanguage: String, // ko
    val originalTitle: String, // 20 Century Girl
    val overview: String, // Yeon-du asks her best friend Bora to collect all the information she can about Baek Hyun-jin while she is away in the U.S. for heart surgery. Bora decides to get close to Baek's best friend, Pung Woon-ho first. However, Bora's clumsy plan unfolds in an unexpected direction. In 1999, a year before the new century, Bora, who turns seventeen, falls into the fever of first love.
    val popularity: Double, // 481.445
    val posterPath: String, // /od22ftNnyag0TTxcnJhlsu3aLoU.jpg
    val releaseDate: String, // 2022-10-06
    val title: String, // 20th Century Girl
    val video: Boolean, // false
    val voteAverage: Double, // 8.9
    val voteCount: Int, // 212
)



//
//fun List<MovieResponse.Result>.asDomineModel(): List<MovieDomin> {
//    return map {
//        MovieDomin(
//            backdropPath = it.backdropPath,
//            adult = it.adult,
//            id = it.id,
//            originalLanguage = it.originalLanguage,
//            originalTitle = it.originalTitle,
//            overview = it.overview,
//            popularity = it.popularity,
//            posterPath = it.posterPath,
//            releaseDate = it.releaseDate,
//            title = it.title,
//            video = it.video,
//            voteAverage = it.voteAverage,
//            voteCount = it.voteCount
//        )
//    }
//}