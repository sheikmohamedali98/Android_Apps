package com.example.newsapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataDao {

//    @Insert()
//    suspend fun insert(Vi)

    @Query("SELECT * FROM news_database")
    fun  getNews():List<DatabaseData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(vararg news:DatabaseData)

}