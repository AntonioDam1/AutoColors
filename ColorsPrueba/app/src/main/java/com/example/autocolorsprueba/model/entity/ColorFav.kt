package com.example.autocolorsprueba.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ColorFav(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    @ColumnInfo(name = "NOMBRE") val nombre: String?,
    @ColumnInfo(name = "AÑO") val year: Int?,
    @ColumnInfo(name = "MARCA") val marca: String?,
    @ColumnInfo(name = "MODELO") val modelo: String?,
    @ColumnInfo(name = "HEXADECIMAL") val hexadecimal: String,
    @ColumnInfo(name = "CODIGO") val codigo: String?,
    @ColumnInfo(name = "CATALOGO_URL") val catalogueURL: String?,
    @ColumnInfo(name = "RED") val red: Int?,
    @ColumnInfo(name = "GREEN") val green: Int?,
    @ColumnInfo(name = "BLUE") val blue: Int?,
    @ColumnInfo(name = "MATCHPERCENTAGE") val matchPercentage: String?
)
