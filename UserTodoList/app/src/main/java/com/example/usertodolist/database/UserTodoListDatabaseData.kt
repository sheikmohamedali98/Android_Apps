package com.example.usertodolist.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.usertodolist.domine.UserResponse
import com.example.usertodolist.domine.UserTodoResponse
import com.squareup.moshi.Json
@Entity(tableName = "user_todo_list")
data class UserTodoListDatabaseData(
    val dueOn: String,
    @PrimaryKey
    val id: Int,
    val status: String,
    val title: String,
    val userId: Int
)

fun List<UserTodoListDatabaseData>.asDomainModel():List<UserTodoResponse>{
    return map{
        UserTodoResponse(
            id = it.id,
           status = it.status,
           title = it.title,
            userId = it.userId,
            dueOn = it.dueOn
        )
    }
}