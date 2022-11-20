package com.example.movieapppagination.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.movieapppagination.api.MovieNetWork
import com.example.movieapppagination.pagination.MoviePagingSource



const val NETWORK_PAGE_SIZE = 25
class MovieListViewModel(val api:MovieNetWork) : ViewModel() {

    val movieList = Pager(
        PagingConfig(pageSize = NETWORK_PAGE_SIZE,
        enablePlaceholders = false)){
            MoviePagingSource(api)
        }.flow.cachedIn(viewModelScope)


}