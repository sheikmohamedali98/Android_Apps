package com.example.miniyoutube.database

import android.content.Context
import androidx.room.Room

abstract class DatabaseInstance:RoomDao {
    abstract val roomDao:RoomDao

//    companion object{
//        @Volatile
//        private  val INSTANCE:DatabaseInstance? = null
//
//        fun getInstance(context:Context):DatabaseInstance{
//            synchronized(this){
//                var instance = INSTANCE
//                if(instance == null){
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        DatabaseInstance::class.java,
//                        "DatabaseInstance"
//                    ).fallbackToDestructiveMigration().build()
//                }
//                INSTANCE = instance
//                return  instance
//            }
//        }
//    }
}