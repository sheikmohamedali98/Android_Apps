package com.example.contact.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.contact.database.Contact
import com.example.contact.database.ContactDatabase
import com.example.contact.repository.ContactRepository

class SearchViewmodel(application: Application):AndroidViewModel(application) {

    val database = ContactDatabase.getInstace(application)
    val repository = ContactRepository(database)

    fun searchContactList(searchQuery:String): LiveData<List<Contact>> {
        return repository.searchContact(searchQuery)
    }
}