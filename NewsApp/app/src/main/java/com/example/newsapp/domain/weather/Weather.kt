package com.example.newsapp.domain.weather

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)