package com.example.autocolorsprueba.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class ColorFav(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    @ColumnInfo(name = "AÑO") val anio: Int?,
    @ColumnInfo(name = "MARCA") val marca: String,
    @ColumnInfo(name = "MODELO") val modelo: String?,
    @ColumnInfo(name = "NOMBREPINTURA") val nombrePintura: String,
    @ColumnInfo(name = "CODIGO") val hexadecimal: String,
    @ColumnInfo(name = "CATALOGO_URL") val catalogueURL: String?,
    @ColumnInfo(name = "HEXADECIMAL") val codigo: String?,
    @ColumnInfo(name = "RED") val red: Int,
    @ColumnInfo(name = "GREEN") val green: Int,
    @ColumnInfo(name = "BLUE") val blue: Int,
    @ColumnInfo(name = "CATALOGO_URL") val colorsampleURL: String,
    @ColumnInfo(name = "MATCHPERCENTAGE") val matchPercentage: String?
): Serializable
