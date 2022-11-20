package com.example.movieapppagination.network


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


//data class ResponseItems<T>(
//    @field:Json(name = "results") val results: List<T>
//)

@JsonClass(generateAdapter = true)
data class MovieResponse(
    @Json(name = "page")
    val page: Int, // 1
    @Json(name = "results")
    val results: List<Result>,
    @Json(name = "total_pages")
    val totalPages: Int, // 525
    @Json(name = "total_results")
    val totalResults: Int // 10487
)