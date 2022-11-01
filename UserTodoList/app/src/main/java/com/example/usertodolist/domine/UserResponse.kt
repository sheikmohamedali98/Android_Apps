package com.example.usertodolist.domine


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class UserResponse (
        val id: Int,
        val email: String,
        val gender: String,
        val name: String,
        val status: String
    )
