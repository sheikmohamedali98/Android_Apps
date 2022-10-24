package com.example.newsapp.network


data class NewsResponse(
    val category: String,
    val data: List<Data>,
    val success: Boolean
)