package com.example.usertodolist.domine

import androidx.room.PrimaryKey

data class UserTodoResponse (
    val dueOn: String?,
    val id: Int?,
    val status: String?,
    val title: String?,
    val userId: Int?
    )

fun getNotAvailableResponse():UserTodoResponse = UserTodoResponse("No Data Available",null, "", "", null)