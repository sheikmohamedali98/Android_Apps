package com.example.usertodolist.networkdata


import com.example.usertodolist.database.UserTodoListDatabaseData
import com.example.usertodolist.database.UserdatabaseData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class UserToDoListNetWorkData (
        @Json(name = "due_on")
        val dueOn: String, // 2022-11-17T00:00:00.000+05:30
        @Json(name = "id")
        val id: Int, // 2082
        @Json(name = "status")
        val status: String, // completed
        @Json(name = "title")
        val title: String, // Apostolus aequus curia spargo suppellex vester tergiversatio deprecator comparo sit.
        @Json(name = "user_id")
        val userId: Int // 4260
    )

fun List<UserToDoListNetWorkData>.asDatabaseModel():Array<UserTodoListDatabaseData>{
        return map{
               UserTodoListDatabaseData(
                        id = it.id,
                        status = it.status,
                       userId = it.userId,
                       title = it.title,
                       dueOn = it.dueOn
                )
        }.toTypedArray()
}
