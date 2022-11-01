package com.example.usertodolist.repositery

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.usertodolist.api.UserApiConnect
import com.example.usertodolist.api.UserTodoApiConnect
import com.example.usertodolist.database.UserDataBase
import com.example.usertodolist.database.UserTodoListDatabaseData
import com.example.usertodolist.database.UserdatabaseData
import com.example.usertodolist.database.asDomainModel
import com.example.usertodolist.domine.UserResponse
import com.example.usertodolist.domine.UserTodoResponse
import com.example.usertodolist.networkdata.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRePositry(private val database: UserDataBase){

    val UserList:LiveData<List<UserResponse>> = Transformations.map(database.roomDao.getUserList()){
        it.asDomainModel()
    }

    fun getTodo(id:Int):LiveData<List<UserTodoResponse>> =Transformations.map(database.roomDao.getTodo(id)){
        it.asDomainModel()
    }

    suspend fun refreshData(){
        withContext(Dispatchers.IO){
            val userList = UserApiConnect.retrofitService.getUserDetails()
            val listData = userList.body()
            if(userList.isSuccessful){
//                respose cime user reference cover
                if (listData != null) {
                    database.roomDao.insertAll(*listData.asDatabaseModel())
                }
            }
        }
    }


   suspend fun refrestTodoList(){

       withContext(Dispatchers.IO) {
           val todoList = UserTodoApiConnect.retrofitService.getTodoList()
           val list = todoList.body()
//           println("\n\n\n\n${getTodo(3626)}\n\n\n\n")
//           println("\n\n\n\n${list.toString()}\n\n\n")
           if (todoList.isSuccessful) {
               if (list != null) {
                   database.roomDao.insertAllTodo(*list.asDatabaseModel())
               }
           }
       }
    }
}



