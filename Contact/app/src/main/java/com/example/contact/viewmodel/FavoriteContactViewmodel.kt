package com.example.contact.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.contact.database.Contact
import com.example.contact.database.ContactDatabase
import com.example.contact.repository.ContactRepository

class FavoriteContactViewmodel(application: Application):AndroidViewModel(application) {

    val database = ContactDatabase.getInstace(application)
    val repository = ContactRepository(database)

    val list = repository.getfavoriteContact(true)
}