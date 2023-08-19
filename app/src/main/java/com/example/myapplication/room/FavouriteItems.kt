package com.example.myapplication.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Favourite_Items")
data class FavouriteItems(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    val img:String?=null,
    val prod_name:String?=null,
    val prod_color:Int?=null,
    val prod_colorName:String?=null,
    val prod_size:String?=null,

    @ColumnInfo(name = "Price")
    val prod_Price:Int?=null
)
