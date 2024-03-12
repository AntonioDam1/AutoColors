package com.example.autocolorsprueba.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.autocolorsprueba.model.entity.ColorFav

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad ColorFav en la base de datos Room.
 */
@Dao
interface ColorFavDao {

    /**
     * Obtiene todos los colores favoritos de la base de datos.
     * @return Una lista mutable de todos los colores favoritos almacenados en la base de datos.
     */
    @Query("SELECT * FROM ColorFav")
    fun getAll(): MutableList<ColorFav>

    /**
     * Carga los colores favoritos por sus IDs.
     * @param userIds Un array de IDs de usuarios.
     * @return Una lista de colores favoritos cuyos IDs coinciden con los proporcionados.
     */
    @Query("SELECT * FROM ColorFav WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<ColorFav>

    /**
     * Inserta uno o más colores favoritos en la base de datos.
     * @param users Los colores favoritos que se van a insertar en la base de datos.
     */
    @Insert
    fun insertAll(vararg users: ColorFav)

    /**
     * Inserta un color favorito en la base de datos.
     * @param users El color favorito que se va a insertar en la base de datos.
     */
    @Insert
    fun insert(vararg users: ColorFav)

    /**
     * Elimina un color favorito de la base de datos.
     * @param user El color favorito que se va a eliminar de la base de datos.
     */
    @Delete
    fun delete(user: ColorFav)
}
