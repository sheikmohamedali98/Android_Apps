package com.example.miniyoutube.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.miniyoutube.DatabaseVideo

@Dao
interface RoomDao {

    @Query("SELECT * FROM databasevideo")
    fun getVideo():LiveData<List<DatabaseVideo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videoModel: DatabaseVideo)

}

@Database(entities = [DatabaseVideo::class], version = 1, exportSchema = false)
abstract class VideoDatabase:RoomDatabase(){
    abstract  val roomDao:RoomDao
}

private  lateinit var INSTACE:VideoDatabase

fun getInstance(context: Context):VideoDatabase{
    synchronized(VideoDatabase::class.java){
        if(!::INSTACE.isInitialized){
            INSTACE = Room.databaseBuilder(context.applicationContext,VideoDatabase::class.java,"videos").build()
        }
        return INSTACE
    }

}