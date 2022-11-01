package com.example.usertodolist.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.usertodolist.database.getInstance
import com.example.usertodolist.domine.UserResponse
import com.example.usertodolist.repositery.UserRePositry
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(application: Application):AndroidViewModel(application) {

//    private val _response = MutableLiveData<Response<List<UserResponse>>>()
//    val response:LiveData<Response<List<UserResponse>>>
//    get() = _response

//
//    private val _navigate = MutableLiveData<Int>()
//    val navigate:LiveData<Int>
//    get() = _navigate

    private val database = getInstance(application)
    private val userRepository = UserRePositry(database)


    init {
        viewModelScope.launch {
            userRepository.refreshData()
        }
    }
    val listdata = userRepository.UserList



}