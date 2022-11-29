package com.example.contact.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase:RoomDatabase() {

    abstract fun contactDao():ContactDao

    companion object{
        @Volatile
        private var INSTACE:ContactDatabase? = null

        fun getInstace(context: Context):ContactDatabase{
            val tempInstance = INSTACE
            if(tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                val instace = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "Contact"
                ).fallbackToDestructiveMigration().build()
                 INSTACE = instace
                return instace
            }
        }
    }
}