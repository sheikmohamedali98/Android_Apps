package com.example.hotelwithoutrecycleview.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MenuDao {

    @Insert
    suspend fun insert(menuItem: MenuItem)

    @Query("SELECT * FROM Menu_table ORDER BY itemId DESC")
    fun getAllMenuItem():LiveData<List<MenuItem>>

}