package com.example.autocolorsprueba.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Esta clase representa la entidad ColorCoche en la base de datos.
 * Contiene información sobre un color de coche.
 *
 * @param uid El identificador único del color del coche.
 * @param anio El año del coche al que pertenece el color.
 * @param marca La marca del coche al que pertenece el color.
 * @param modelo El modelo del coche al que pertenece el color.
 * @param nombrePintura El nombre del color de pintura del coche.
 * @param codigo El código del color de pintura del coche.
 * @param catalogueURL La URL del catálogo del coche.
 * @param hexadecimal El valor hexadecimal del color de pintura del coche.
 * @param red El componente rojo del color de pintura del coche.
 * @param green El componente verde del color de pintura del coche.
 * @param blue El componente azul del color de pintura del coche.
 * @param colorsampleURL La URL de la muestra de color del coche.
 * @param matchPercentage El porcentaje de coincidencia del color de pintura del coche, puede ser nulo.
 */
@Entity
data class ColorCoche(
    @PrimaryKey val uid: Long,
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
    )

