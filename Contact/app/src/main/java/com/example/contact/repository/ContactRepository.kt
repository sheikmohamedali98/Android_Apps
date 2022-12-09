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

    fun getContact(id:Int):LiveData<Contact>{
        return  contactDatabase.contactDao().getContact(id)
    }

    suspend fun updateContact(contact: Contact){
        contactDatabase.contactDao().updateContact(contact)
    }
    suspend fun updateFavorite(isChecked:Boolean,id:Int){
        contactDatabase.contactDao().updateFavorite(isChecked,id)
    }

    fun getfavoriteContact(isChecked: Boolean):LiveData<List<Contact>>{
        return  contactDatabase.contactDao().getFavoriteContact(isChecked)
    }
}