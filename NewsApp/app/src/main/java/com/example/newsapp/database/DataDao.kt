package com.example.newsapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.network.NewsResponse

@Dao
interface DataDao {

//    @Insert()
//    suspend fun insert(Vi)

    @Query("SELECT * FROM news_database")
    fun  getNews():LiveData<List<DatabaseData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(vararg news: DatabaseData?)

    @Query("DELETE FROM news_database")
    fun deletNews()


}