package com.example.contact.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.contact.database.Contact
import com.example.contact.database.ContactDatabase
import com.example.contact.repository.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(application: Application):AndroidViewModel(application) {


     val readAllContact:LiveData<List<Contact>>


    val database = ContactDatabase.getInstace(application)
    val repository = ContactRepository(database)

    init {
        readAllContact = repository.getContacts()
    }

    fun searchContactList(searchQuery:String):LiveData<List<Contact>>{
       return repository.searchContact(searchQuery)
    }



    fun deleteAllContact(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteContact()
        }
    }

    fun addContact(contact: Contact){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addContact(contact)
        }

    }

    fun deletteSingleContact(contact: Contact){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteSingleContact(contact)
        }
    }
}