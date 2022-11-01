package com.example.usertodolist.networkdata

import com.example.usertodolist.database.UserdatabaseData
import com.squareup.moshi.Json

data class UserNerWorkdata (
    @Json(name = "email")
    val email: String, // nanda_deshpande@hermann-johns.co
    @Json(name = "gender")
    val gender: String, // male
    @Json(name = "id")
    val id: Int, // 4263
    @Json(name = "name")
    val name: String, // Nanda Deshpande
    @Json(name = "status")
    val status: String // active
        )

fun List<UserNerWorkdata>.asDatabaseModel():Array<UserdatabaseData>{
    return map{
        UserdatabaseData(
         id = it.id,
            name = it.name,
            status = it.status,
            email = it.email,
            gender = it.gender

        )
    }.toTypedArray()
}

