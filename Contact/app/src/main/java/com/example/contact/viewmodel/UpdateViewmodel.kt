package com.example.contact.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.contact.database.Contact
import com.example.contact.database.ContactDatabase
import com.example.contact.repository.ContactRepository
import kotlinx.coroutines.launch

class UpdateViewmodel(application: Application):AndroidViewModel(application) {

    val database = ContactDatabase.getInstace(application)
    val repository = ContactRepository(database)



    fun updateContact(contact: Contact){
        viewModelScope.launch {
            repository.updateContact(contact)
        }
    }
    fun getContact(id:Int):LiveData<Contact>{
        return repository.getContact(id)
    }
}