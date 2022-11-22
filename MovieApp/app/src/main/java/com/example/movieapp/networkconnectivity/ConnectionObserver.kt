package com.example.movieapp.networkconnectivity

import kotlinx.coroutines.flow.Flow

interface ConnectionObserver {
    fun observer():Flow<Status>
    enum class Status{
        Available,Unavailable,Lost,Losing

    }
}