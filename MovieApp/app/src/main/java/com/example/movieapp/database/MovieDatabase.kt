package com.example.movieapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieDatabasaData::class], version = 1, exportSchema = false)
abstract class MovieDatabase:RoomDatabase() {
    abstract  val  roomDao:MovieDao
}

private lateinit var INSTANCE:MovieDatabase

fun getInstace(context: Context):MovieDatabase{
    synchronized(MovieDatabase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "Movie"
            ).build()
        }
    }
    return INSTANCE
}


