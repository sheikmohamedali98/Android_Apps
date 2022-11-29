package com.example.contact.repository

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.contact.database.Contact
import com.example.contact.database.ContactDao
import com.example.contact.database.ContactDatabase

class ContactRepository (private val contactDatabase: ContactDatabase){



    fun searchContact(searchQuery:String):LiveData<List<Contact>>{
        return contactDatabase.contactDao().searchContact(searchQuery)
    }

    suspend fun deleteContact(){
        contactDatabase.contactDao().deleteContact()
    }
    suspend fun addContact(contact: Contact){
        contactDatabase.contactDao().insertContact(contact)
    }
     fun getContacts():LiveData<List<Contact>>{
        return  contactDatabase.contactDao().getContacts()
    }

    suspend fun deleteSingleContact(contact: Contact){
        contactDatabase.contactDao().deleteSingleContact(contact)
    }
}