package com.example.agecalculator

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AgeViewModelFactory(private  val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AgeViewModel::class.java)){
            return AgeViewModel(context) as T
        }
        throw  IllegalArgumentException("")
    }
}