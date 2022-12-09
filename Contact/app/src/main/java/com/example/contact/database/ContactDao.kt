package com.example.contact.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Delete
    suspend fun deleteSingleContact(contact: Contact)

    @Query("DELETE FROM contact" )
    suspend fun deleteContact()

    @Query("SELECT * From contact ORDER BY firstname ASC")
     fun getContacts():LiveData<List<Contact>>

    @Query("SELECT * From contact where id = :id")
     fun getContact(id:Int):LiveData<Contact>

     @Query("SELECT * FROM contact WHERE firstname LIKE :searchQuery or company Like :searchQuery")
     fun searchContact(searchQuery:String):LiveData<List<Contact>>

     @Update
     suspend fun updateContact(contact: Contact)

     @Query("UPDATE contact SET isFavorite = :ischecked WHERE id = :id ")
     suspend fun updateFavorite(ischecked:Boolean,id:Int)

     @Query("SELECT * FROM contact WHERE isFavorite =:isChecked ORDER BY firstname ASC")
     fun getFavoriteContact(isChecked:Boolean):LiveData<List<Contact>>

}