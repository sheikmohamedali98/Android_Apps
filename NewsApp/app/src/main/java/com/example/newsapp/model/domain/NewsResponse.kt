package com.example.newsapp.model.domain


data class NewsResponse(
    val category: String,
    val data: List<Data>,
    val success: Boolean
)