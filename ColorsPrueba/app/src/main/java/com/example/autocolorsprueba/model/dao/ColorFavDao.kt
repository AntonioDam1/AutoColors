package com.example.autocolorsprueba.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.autocolorsprueba.model.entity.ColorFav


@Dao
interface ColorFavDao {
    @Query("SELECT * FROM ColorFav")
    fun getAll(): MutableList<ColorFav>

    @Query("SELECT * FROM ColorFav  WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<ColorFav>

    @Insert
    fun insertAll(vararg users: ColorFav)
    @Insert
    fun insert(vararg users: ColorFav)

    @Delete
    fun delete(user: ColorFav)
}