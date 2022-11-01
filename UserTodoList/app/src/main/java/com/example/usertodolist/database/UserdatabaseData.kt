package com.example.usertodolist.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.usertodolist.domine.UserResponse
import com.squareup.moshi.Json

@Entity(tableName = "user_details")
data class UserdatabaseData (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val email: String,
    val gender: String,
    val name: String,
    val status: String
    )


fun List<UserdatabaseData>.asDomainModel():List<UserResponse>{
    return map{
        UserResponse(
            id = it.id,
            name = it.name,
           email = it.email,
            gender = it.gender,
            status = it.status
        )
    }
}