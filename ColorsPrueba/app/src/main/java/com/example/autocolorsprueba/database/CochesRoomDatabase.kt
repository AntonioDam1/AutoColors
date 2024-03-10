package com.example.autocolorsprueba.database

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.autocolorsprueba.model.dao.ColorCocheDao
import com.example.autocolorsprueba.model.dao.ColorFavDao
import com.example.autocolorsprueba.model.entity.ColorCoche
import com.example.autocolorsprueba.model.entity.ColorFav

import kotlin.concurrent.Volatile

@Database(entities = [ColorCoche::class, ColorFav::class], version = 10)
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
                    instance = Room.databaseBuilder(context.applicationContext, CochesRoomDatabase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }


        }


//        private val MIGRATION_2_4 = object : Migration(2, 4) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                // Crea la nueva tabla 'color_coche' con los mismos campos que 'color_fav'
//                database.execSQL(
//                    "DROP TABLE IF EXISTS `ColorCoche`" +
//                    "CREATE TABLE IF NOT EXISTS `ColorCoche` (" +
//                            "`uid` INTEGER PRIMARY KEY  NOT NULL, " +
//                            "`AÑO` INTEGER, " +
//                            "`MARCA` TEXT NOT NULL, " +
//                            "`MODELO` TEXT, " +
//                            "`NOMBREPINTURA` TEXT, " +
//                            "`CODIGO` TEXT, " +
//                            "`CATALOGO_URL` TEXT, " +
//                            "`HEXADECIMAL` TEXT NOT NULL, " +
//                            "`RED` INTEGER NOT NULL, " +
//                            "`GREEN` INTEGER NOT NULL, " +
//                            "`BLUE` INTEGER NOT NULL, " +
//                            "`COLORSAMPLE_URL` TEXT, " +
//                            "`MATCHPERCENTAGE` TEXT)"
//                )
//            }
//        }

//        private val MIGRATION_2_3 = object : Migration(2, 3) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                // Aquí defines cómo migrar los datos de la versión 2 a la versión 3
//            }
//        }
//
//        private val MIGRATION_2_4 = object : Migration(2, 4) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                // Aquí defines cómo migrar los datos de la versión 3 a la versión 4
//                database.execSQL(
//                    "DROP TABLE IF EXISTS `ColorCoche`" +
//                            "CREATE TABLE IF NOT EXISTS `ColorCoche` (" +
//                            "`uid` INTEGER PRIMARY KEY  NOT NULL, " +
//                            "`AÑO` INTEGER, " +
//                            "`MARCA` TEXT NOT NULL, " +
//                            "`MODELO` TEXT, " +
//                            "`NOMBREPINTURA` TEXT, " +
//                            "`CODIGO` TEXT, " +
//                            "`CATALOGO_URL` TEXT, " +
//                            "`HEXADECIMAL` TEXT NOT NULL, " +
//                            "`RED` INTEGER NOT NULL, " +
//                            "`GREEN` INTEGER NOT NULL, " +
//                            "`BLUE` INTEGER NOT NULL, " +
//                            "`COLORSAMPLE_URL` TEXT, " +
//                            "`MATCHPERCENTAGE` TEXT)"
//                )
//            }
//        }
    }
}
