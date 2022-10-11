package com.example.hotel.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Menu_list")
data class MenuItem(
    @PrimaryKey(autoGenerate = true)
    val itemId: Int = 0,
    @ColumnInfo(name = "item_name")
    var itemName: String,
    @ColumnInfo(name = "itemPrice")
    var itemPrice: Double
)
