package com.example.hotel.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MenuDao {

    @Insert
    fun addMenu(menuItem: MenuItem)

    @Query("DELETE FROM Menu_list")
     fun deleteItem()

    @Query("SELECT * FROM Menu_list Order by itemId ASC")
    fun displayItem():LiveData<List<MenuItem>>

}