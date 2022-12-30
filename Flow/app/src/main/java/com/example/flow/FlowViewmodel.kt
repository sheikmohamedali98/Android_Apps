package com.example.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FlowViewmodel:ViewModel() {

   private val _stateFlow = MutableStateFlow<String>("State Started")
    val stateFlow:StateFlow<String> = _stateFlow

    fun updateState(value:String){
        _stateFlow.value = value
    }


    private val _shareFlow = MutableSharedFlow<String>()
    val sharedFlow:SharedFlow<String> = _shareFlow

     fun updateShare(value:String){
        viewModelScope.launch {
            _shareFlow.emit(value)
        }
    }


}