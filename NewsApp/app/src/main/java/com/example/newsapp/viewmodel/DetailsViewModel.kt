package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.database.getInstance
import com.example.newsapp.domain.DomainData
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.ui.DetailNewsFragmentArgs

class DetailsViewModel(domainData: DomainData, application: Application):AndroidViewModel(application) {
    private  val _selcectedNews= MutableLiveData<DomainData>()

    val selectedNews:LiveData<DomainData>
    get() = _selcectedNews

//    private  val database = getInstance(application)
//    private  val newsRepository = NewsRepository(database)

    init {
        _selcectedNews.value = domainData
    }
}