package com.example.contact.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertContact(contact: Contact)

    @Delete
    suspend fun deleteSingleContact(contact: Contact)

    @Query("DELETE FROM contact" )
    suspend fun deleteContact()

    @Query("SELECT * From contact ORDER BY firstname ASC")
     fun getContacts():LiveData<List<Contact>>

     @Query("SELECT * FROM contact WHERE firstname LIKE :searchQuery or company Like :searchQuery")
     fun searchContact(searchQuery:String):LiveData<List<Contact>>
}