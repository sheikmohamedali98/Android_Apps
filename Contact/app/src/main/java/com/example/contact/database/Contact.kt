package com.example.contact.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id:Int ,
    val firstname:String,
    val phonenumber:String,
    val  company:String,
    val imageUrl:String = ""
)
