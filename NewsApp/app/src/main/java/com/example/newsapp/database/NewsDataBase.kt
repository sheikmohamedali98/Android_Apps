package com.example.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseData::class], version = 2, exportSchema = false)
abstract class NewsDataBase:RoomDatabase (){
    abstract  val dataDao:DataDao
}

private lateinit var INSTANCE:NewsDataBase

fun getInstance(context: Context):NewsDataBase{
synchronized(NewsDataBase::class.java){
    if(!::INSTANCE.isInitialized){
        INSTANCE = Room.databaseBuilder(
            context.applicationContext,
            NewsDataBase::class.java,
            "news_database"
        ).fallbackToDestructiveMigration()
            .build()
    }
}
    return INSTANCE
}

