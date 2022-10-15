package com.example.retrofitpractice.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpractice.model.Post
import com.example.retrofitpractice.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {
    private val _myResponce = MutableLiveData<Response<Post>>()

    val myResponce:LiveData<Response<Post>>
            get() = _myResponce

    private val _myResponce2 = MutableLiveData<Response<Post>>()

    val myResponce2:LiveData<Response<Post>>
    get() = _myResponce2

    private val _myCustomePost = MutableLiveData<Response<List<Post>>>()

    val myCustomePost:LiveData<Response<List<Post>>>
    get() = _myCustomePost

    private val _myCustomePost2 = MutableLiveData<Response<List<Post>>>()

    val myCustomePost2:LiveData<Response<List<Post>>>
        get() = _myCustomePost2

    fun getPost(){
        viewModelScope.launch {
            val response  = repository.getPost()
            _myResponce.value = response
        }
    }

    fun  getPost2(number:Int){
        viewModelScope.launch {
            val response = repository.getPost2(number)
            _myResponce2.value = response
        }
    }
    fun getCustomePost(userId:Int){
        viewModelScope.launch {
            val response = repository.getCustomePost(userId)
           _myCustomePost.value = response
        }
    }
    fun getCustomePost2(userId:Int,sort:String,order:String){
        viewModelScope.launch {
            val response = repository.getCustomePost2(userId,sort,order)
            _myCustomePost.value = response
        }
    }

    fun getCustomPost3(userId:Int,option:Map<String,String>){
        viewModelScope.launch {
            val response = repository.getCustomePost3(userId, option)
            _myCustomePost2.value = response
        }
    }
}