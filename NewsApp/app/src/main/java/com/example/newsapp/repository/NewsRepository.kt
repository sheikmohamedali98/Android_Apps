package com.example.newsapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.newsapp.api.NewsApi
import com.example.newsapp.api.NewsFilter
import com.example.newsapp.database.NewsDataBase
import com.example.newsapp.database.asDomainModel
import com.example.newsapp.domain.DomainData
import com.example.newsapp.network.Data
import com.example.newsapp.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(private  val database:NewsDataBase) {


    val newsList:LiveData<List<DomainData>> = Transformations.map(database.dataDao.getNews()){
        it.asDomainModel()
    }
//    fun checkPrint(){
//        println("\n\n\n print neew List iNNreReposi ${newsList}\n\n\n")
//    }

    suspend fun refreshNews(filter:String){
        withContext(Dispatchers.IO){
            val newsList = NewsApi.retrofitService.getAllNews(filter)
            val list = newsList.body()?.data

            if(newsList.isSuccessful){
                if (list != null) {
                    println("\n\n\n\n${newsList.body()?.data}\n\n\n\n")
                    database.dataDao.insertNews(*list.asDatabaseModel().toTypedArray())
                }
            }

        }
    }
}

