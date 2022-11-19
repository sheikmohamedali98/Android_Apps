package com.example.newsapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.domain.DomainData
import com.example.newsapp.network.NewsResponse
import java.util.concurrent.Flow

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

    @Query("SELECT * FROM  news_database WHERE  title Like :subQuery")
    fun searchDatabase(subQuery:String):List<DatabaseData>


}