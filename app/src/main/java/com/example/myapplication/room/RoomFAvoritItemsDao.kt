package com.example.myapplication.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomFAvoritItemsDao {

    @Insert
    fun addToFavourits(favouriteItems: FavouriteItems)

    @Query("SELECT * FROM Favourite_Items")
    fun showAllFromFavourits():List<FavouriteItems>

    @Query("DELETE FROM Favourite_Items")
    fun deleteAllFromDataBase()

    @Delete
    fun deleteItemFromFav(favouriteItems: FavouriteItems)

    @Query("SELECT Price FROM Favourite_Items")
    fun getAllPrices():List<Int>


}