package com.example.networkabserver

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observer():Flow<Status>

    enum class Status{
        Available,Unavailable,Lost,Losing
    }


}