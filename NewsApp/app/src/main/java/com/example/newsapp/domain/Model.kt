package com.example.newsapp.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DomainData(
    val author: String?,
    val content: String?,
    val date: String?,
    val id: String?,
    val imageUrl: String?,
    val readMoreUrl: String?,
    val time: String?,
    val title: String?,
    val url: String?
) : Parcelable