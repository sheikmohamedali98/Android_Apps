package com.example.hotelwithoutrecycleview.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Menu_table")
data class MenuItem(
    @PrimaryKey(autoGenerate = true)
    var itemId: Int = 0,
    @ColumnInfo(name = "itemName")
    var itemName: String ,
    @ColumnInfo(name = "itemPrice")
    var itemPrice: String,
)


