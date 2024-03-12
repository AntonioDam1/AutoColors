package com.example.autocolorsprueba.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Esta clase representa la entidad ColorFav en la base de datos.
 * Contiene información sobre un color de coche marcado como favorito.
 *
 * @param uid El identificador único del color favorito del coche.
 * @param anio El año del coche al que pertenece el color favorito.
 * @param marca La marca del coche al que pertenece el color favorito.
 * @param modelo El modelo del coche al que pertenece el color favorito.
 * @param nombrePintura El nombre del color de pintura del coche favorito.
 * @param codigo El código del color de pintura del coche favorito.
 * @param catalogueURL La URL del catálogo del coche favorito.
 * @param hexadecimal El valor hexadecimal del color de pintura del coche favorito.
 * @param red El componente rojo del color de pintura del coche favorito.
 * @param green El componente verde del color de pintura del coche favorito.
 * @param blue El componente azul del color de pintura del coche favorito.
 * @param colorsampleURL La URL de la muestra de color del coche favorito.
 * @param matchPercentage El porcentaje de coincidencia del color de pintura del coche favorito, puede ser nulo.
 */
@Entity
data class ColorFav(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    @ColumnInfo(name = "AÑO") val anio: Int?,
    @ColumnInfo(name = "MARCA") val marca: String?,
    @ColumnInfo(name = "MODELO") val modelo: String?,
    @ColumnInfo(name = "NOMBREPINTURA") val nombrePintura: String?,
    @ColumnInfo(name = "CODIGO") val codigo: String?,
    @ColumnInfo(name = "CATALOGO_URL") val catalogueURL: String?,
    @ColumnInfo(name = "HEXADECIMAL") val hexadecimal: String?,
    @ColumnInfo(name = "RED") val red: Int,
    @ColumnInfo(name = "GREEN") val green: Int,
    @ColumnInfo(name = "BLUE") val blue: Int,
    @ColumnInfo(name = "COLORSAMPLE_URL") val colorsampleURL: String?,
    @ColumnInfo(name = "MATCHPERCENTAGE") val matchPercentage: Double?
): Serializable
