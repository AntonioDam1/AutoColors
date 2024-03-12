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

/**
 * Clase que crea y representa la base de datos de la aplicación para almacenar colores e insertar a favoritos o a ColoresCoche
 * Esta base de datos utiliza la arquitectura de Room, que es una capa de abstracción sobre SQLite.
 * Proporciona métodos para acceder a las instancias de los DAOs para realizar operaciones CRUD en las tablas de la base de datos.
 * Aquí se declara el nombre de las tablas y sus DAO
 *
 * @property DATABASE_NAME El nombre de la base de datos.
 */
@Database(entities = [ColorCoche::class, ColorFav::class], version = 10)
abstract class CochesRoomDatabase : RoomDatabase() {
    abstract fun colorCocheDao(): ColorCocheDao
    abstract fun colorFavDao(): ColorFavDao


    companion object {
        private const val DATABASE_NAME = "AUTOCOLORFAVS.db"

        @Volatile
        private var INSTANCE: CochesRoomDatabase? = null

        /**
         * Función para obtener una instancia de la base de datos.
         * @param context
         * @return Una instancia de la base de datos cocheRoomDatabase
         * Hacemos fallbackDestructiveMigration para no tener que hacer migración de datos al cambiar datos de la columna de las tablas
         */
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
    }
}
