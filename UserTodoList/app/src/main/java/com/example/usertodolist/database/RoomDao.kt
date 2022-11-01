package com.example.usertodolist.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.usertodolist.domine.UserResponse

@Dao
interface RoomDao {
    @Query("SELECT * FROM user_details")
    fun getUserList(): LiveData<List<UserdatabaseData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg userResponse: UserdatabaseData)

    @Query("SELECT * FROM user_todo_list")
    fun getUserTodoList(): LiveData<List<UserTodoListDatabaseData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTodo(vararg userResponse: UserTodoListDatabaseData)

    @Query("SELECT * FROM user_todo_list WHERE userId =:id ")
    fun getTodo(id:Int):LiveData<List<UserTodoListDatabaseData>>

}
@Database(entities =[UserdatabaseData::class,UserTodoListDatabaseData::class], version = 2, exportSchema = false)
abstract  class UserDataBase:RoomDatabase() {
abstract val roomDao:RoomDao
}
   private lateinit var  INSTACE:UserDataBase

   fun getInstance(context: Context):UserDataBase{
       synchronized(UserdatabaseData::class.java){
           if(!::INSTACE.isInitialized){
               INSTACE = Room.databaseBuilder(context.applicationContext,UserDataBase::class.java,"user_details").fallbackToDestructiveMigration().build()
           }
       }
       return INSTACE
   }




