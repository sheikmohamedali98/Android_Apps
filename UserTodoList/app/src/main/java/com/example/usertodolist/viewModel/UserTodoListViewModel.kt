package com.example.usertodolist.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.usertodolist.database.getInstance
import com.example.usertodolist.repositery.UserRePositry
import kotlinx.coroutines.launch

class UserTodoListViewModel(id:Int,application: Application):AndroidViewModel(application){

    val database =  getInstance(application)
    val repository = UserRePositry(database)
    val toDolist = repository.getTodo(id)
    init {
        viewModelScope.launch {
            repository.refrestTodoList()
        }
//        println("\n\n\n\n${toDolist.value.toString()}\n\n\n\n")
    }




//    val list = repository.todolist

//    val filterList = repository.filterList
}