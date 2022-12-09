package com.example.contact.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.contact.database.Contact
import com.example.contact.database.ContactDatabase
import com.example.contact.repository.ContactRepository
import kotlinx.coroutines.launch

class DetailContactViewmodel(application: Application):AndroidViewModel(application) {

    val database = ContactDatabase.getInstace(application)
    val repository = ContactRepository(database)


    fun getContact(id:Int):LiveData<Contact>{
        return repository.getContact(id)
    }
    fun updateFavorite(isChecked:Boolean,id:Int){
        viewModelScope.launch {
            repository.updateFavorite(isChecked,id)
        }
    }
}