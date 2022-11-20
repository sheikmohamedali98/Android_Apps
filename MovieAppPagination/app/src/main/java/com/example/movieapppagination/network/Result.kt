package com.example.movieapppagination.network

import com.squareup.moshi.Json

data class Result(
@Json(name = "adult")
val adult: Boolean, // false
@Json(name = "backdrop_path")
val backdropPath: String, // /rl7Jw8PjhSIjArOlDNv0JQPL1ZV.jpg
@Json(name = "genre_ids")
val genreIds: List<Int>,
@Json(name = "id")
val id: Int, // 851644
@Json(name = "original_language")
val originalLanguage: String, // ko
@Json(name = "original_title")
val originalTitle: String, // 20 Century Girl
@Json(name = "overview")
val overview: String, // Yeon-du asks her best friend Bora to collect all the information she can about Baek Hyun-jin while she is away in the U.S. for heart surgery. Bora decides to get close to Baek's best friend, Pung Woon-ho first. However, Bora's clumsy plan unfolds in an unexpected direction. In 1999, a year before the new century, Bora, who turns seventeen, falls into the fever of first love.
@Json(name = "popularity")
val popularity: Double, // 481.445
@Json(name = "poster_path")
val posterPath: String, // /od22ftNnyag0TTxcnJhlsu3aLoU.jpg
@Json(name = "release_date")
val releaseDate: String, // 2022-10-06
@Json(name = "title")
val title: String, // 20th Century Girl
@Json(name = "video")
val video: Boolean, // false
@Json(name = "vote_average")
val voteAverage: Double, // 8.9
@Json(name = "vote_count")
val voteCount: Int // 212
)
