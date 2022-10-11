package com.example.hotelwithoutrecycleview.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [MenuItem::class], version = 1, exportSchema = false)
abstract class MenuDataBase : RoomDatabase() {
    abstract val dataBaseDao: MenuDao

    companion object {

        @Volatile
        private var INSTENCE: MenuDataBase? = null
        fun getInstance(context: Context): MenuDataBase {
            kotlin.synchronized(this) {
                var instence = INSTENCE
                if (instence == null) {
                    instence = Room.databaseBuilder(context.applicationContext,
                        MenuDataBase::class.java,
                        "Menu_table").fallbackToDestructiveMigration().build()
                    INSTENCE = instence
                }
                return instence
            }

        }

    }
}

