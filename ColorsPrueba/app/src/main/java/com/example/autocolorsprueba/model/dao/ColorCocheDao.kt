package com.example.autocolorsprueba.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.autocolorsprueba.model.entity.ColorCoche


@Dao
interface ColorCocheDao {
    @Query("SELECT * FROM ColorCoche")
    fun getAll(): MutableList<ColorCoche>

    @Query("SELECT * FROM colorcoche  WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<ColorCoche>

    @Insert
    fun insertAll( coches: List<ColorCoche>)

    @Delete
    fun delete(user: ColorCoche)
}