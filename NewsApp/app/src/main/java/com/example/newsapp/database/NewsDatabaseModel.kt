package com.example.newsapp.database

import androidx.room.Embedded
import androidx.room.PrimaryKey


data class NewsDatabaseModel(
    @PrimaryKey(autoGenerate = false)
    val category: String,
    @Embedded
    val data: List<DatabaseData> = listOf(),
    val success: Boolean
    )
