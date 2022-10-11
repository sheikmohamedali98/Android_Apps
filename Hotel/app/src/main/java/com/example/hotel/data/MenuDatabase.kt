package com.example.hotel.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [MenuItem::class], version = 2, exportSchema = false)
abstract class MenuDatabase :RoomDatabase(){

    abstract fun menuDao():MenuDao

    companion object{
        @Volatile
        private  var INSTACE:MenuDatabase?= null
        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context):MenuDatabase{
            val tempInstance = INSTACE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MenuDatabase::class.java,
                    "menu"
                ).fallbackToDestructiveMigration().build()
                INSTACE = tempInstance
                return instance
            }
        }
    }

}