package com.example.autocolorsprueba.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.autocolorsprueba.model.entity.ColorCoche

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad ColorCoche en la base de datos Room.
 */
@Dao
interface ColorCocheDao {

    /**
     * Obtiene todos los colores de coche de la base de datos ordenados por porcentaje de coincidencia.
     * @return Una lista mutable de todos los colores de coche almacenados en la base de datos, ordenados por porcentaje de coincidencia.
     */
    @Query("SELECT * FROM ColorCoche ORDER BY MATCHPERCENTAGE")
    fun getAll(): MutableList<ColorCoche>

    /**
     * Carga los colores de coche por sus IDs.
     * @param userIds Un array de IDs de usuarios.
     * @return Una lista de colores de coche cuyos IDs coinciden con los proporcionados.
     */
    @Query("SELECT * FROM ColorCoche WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<ColorCoche>

    /**
     * Inserta una lista de colores de coche en la base de datos.
     * @param coches La lista de colores de coche que se van a insertar en la base de datos.
     */
    @Insert
    fun insertAll(coches: List<ColorCoche>)

    /**
     * Inserta un color de coche en la base de datos.
     * @param coche El color de coche que se va a insertar en la base de datos.
     */
    @Insert
    fun insert(coche: ColorCoche)

    /**
     * Elimina un color de coche de la base de datos.
     * @param coche El color de coche que se va a eliminar de la base de datos.
     */
    @Delete
    fun delete(coche: ColorCoche)

    /**
     * Elimina todos los colores de coche de la base de datos.
     */
    @Query("DELETE FROM ColorCoche")
    fun deleteAll()
}
