package com.example.usertodolist.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserTodoListViewModelFactory(private val id:Int,private val application: Application):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserTodoListViewModel::class.java)){
            return UserTodoListViewModel(id,application) as T
        }
       throw IllegalArgumentException("Modelnot found")
    }
}