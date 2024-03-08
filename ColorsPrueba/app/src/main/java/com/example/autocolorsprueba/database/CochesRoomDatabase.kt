package com.example.autocolorsprueba.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.autocolorsprueba.model.dao.ColorCocheDao
import com.example.autocolorsprueba.model.dao.ColorFavDao
import com.example.autocolorsprueba.model.entity.ColorCoche
import com.example.autocolorsprueba.model.entity.ColorFav

import kotlin.concurrent.Volatile

@Database(entities = [ColorCoche::class, ColorFav::class], version = 4)
abstract class CochesRoomDatabase : RoomDatabase() {
    abstract fun colorCocheDao(): ColorCocheDao
    abstract fun colorFavDao(): ColorFavDao


    companion object {
        private const val DATABASE_NAME = "AUTOCOLORFAVS.db"

        @Volatile
        private var INSTANCE: CochesRoomDatabase? = null

        fun getInstance(context: Context): CochesRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, CochesRoomDatabase::class.java, DATABASE_NAME).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
