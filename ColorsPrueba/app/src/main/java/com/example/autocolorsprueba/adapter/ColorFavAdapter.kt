package com.example.autocolorsprueba.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.autocolorsprueba.ColorCocheDetail
import com.example.autocolorsprueba.ColorStorage
import com.example.autocolorsprueba.R
import com.example.autocolorsprueba.database.CochesRoomDatabase
import com.example.autocolorsprueba.model.entity.ColorCoche
import com.example.autocolorsprueba.model.entity.ColorFav
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Adaptador para la lista de colores de coches favoritos.
 * Este adaptador se utiliza para mostrar la lista de colores de coches favoritos en un RecyclerView.
 *
 * @property colorFavList La lista de colores de coches favoritos que se mostrará en el RecyclerView.
 * @return el recyclerView con los elementos de la lista
 */
class ColorFavAdapter(val colorFavList: MutableList<ColorFav> ) : RecyclerView.Adapter<ColorFavViewHolder>() {

    /**
     * Crea y devuelve un nuevo ViewHolder.
     *
     * @param parent El ViewGroup al que se adjuntará el nuevo ViewHolder.
     * @param viewType El tipo de vista del nuevo ViewHolder.
     * @return El nuevo ViewHolder creado.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorFavViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ColorFavViewHolder(layoutInflater.inflate(R.layout.item_colorcoche, parent, false))

    }

    /**
     * Devuelve el número total de colores que hay en la lista.
     *
     * @return El número total de colores que hay en la lista.
     */
    override fun getItemCount(): Int = colorFavList.size


    /**
     * Actualiza el contenido de un ViewHolder en una posición específica.
     *
     * @param holder El ViewHolder que debe actualizarse.
     * @param position La posición del elemento en la lista de datos.
     */
    override fun onBindViewHolder(holder: ColorFavViewHolder, position: Int) {
        val item = colorFavList[position]
        holder.render(item)

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, ColorCocheDetail::class.java)
            intent.putExtra("origen","fav")
            intent.putExtra("anio", item.anio.toString())
            intent.putExtra("modelo", item.modelo)
            intent.putExtra("codigo", item.codigo)
            intent.putExtra("nombrePintura", item.nombrePintura)

            intent.putExtra("marca", item.marca ?: "N/A")
            intent.putExtra("hexadecimal", item.hexadecimal)
            intent.putExtra("ITEM_KEY",item)
            ColorStorage.setString(item.hexadecimal.toString())
            Log.d("saved", item.hexadecimal.toString())
            holder.itemView.context.startActivity(intent)
        }
    }

}

/**
 * Clase que modela el ViewHolder para un elemento de la lista de colores de coches favoritos.
 * Este ViewHolder se utiliza para mostrar la información de un color de coche favorito en un elemento de la lista del RecyclerView.
 *
 * @property view La vista que contiene los elementos de la interfaz de usuario para mostrar la información del color del coche favorito.
 */
class ColorFavViewHolder(view: View): RecyclerView.ViewHolder(view){
    val hexadecimal = view.findViewById<TextView>(R.id.tvHexadecimal)
    val marca = view.findViewById<TextView>(R.id.tvMarca)
    val codigo = view.findViewById<TextView>(R.id.tvCodigo)
    val match = view.findViewById<TextView>(R.id.tvMatch)
    val imagen = view.findViewById<ImageView>(R.id.colorin)
    val anio = view.findViewById<TextView>(R.id.tvAnio)
    val nombrePintura = view.findViewById<TextView>(R.id.tvNombrePintura)
    val fondoLayout = view.findViewById<ConstraintLayout>(R.id.item_layout)



    /**
     * Método para establecer los datos del color del coche favorito en la interfaz de usuario.
     *
     * @param colorCoche El objeto ColorFav que contiene la información del color del coche favorito.
     */
    fun render(colorCoche: ColorFav){
        hexadecimal.text = colorCoche.hexadecimal?.toUpperCase()
        nombrePintura.text = colorCoche.nombrePintura
        marca.text = colorCoche.marca
        codigo.text = colorCoche.codigo

        val textoAnio = if (colorCoche.anio != null && colorCoche.anio >= 1900) {
            colorCoche.anio
        } else {
            "N/A"
        }

        //Calculamos el color para los textos
        val colorTextoHex = obtenerColorTexto(colorCoche.hexadecimal ?: "#FFFFFF")
        val colorTextoHexParsed = android.graphics.Color.parseColor(colorTextoHex)
        hexadecimal.setTextColor(colorTextoHexParsed)
        nombrePintura.setTextColor(colorTextoHexParsed)
        marca.setTextColor(colorTextoHexParsed)
        codigo.setTextColor(colorTextoHexParsed)
        anio.setTextColor(colorTextoHexParsed)

        anio.text = textoAnio.toString()
        match.isVisible=false
        val colorId = android.graphics.Color.parseColor(colorCoche.hexadecimal)
        fondoLayout.setBackgroundColor(colorId)

        val nombreMarca = colorCoche.marca?.toLowerCase() ?: ""
        val resourceId = if (nombreMarca.isNotEmpty()) {
            imagen.context.resources.getIdentifier(nombreMarca, "drawable", imagen.context.packageName)
        } else {
            // Si no hay marca, establecer el recurso "nologo"
            imagen.context.resources.getIdentifier("nologo", "drawable", imagen.context.packageName)
        }

        if (resourceId != 0) {
            imagen.setImageResource(resourceId)
        } else {
            // Manejar el caso donde no hay un recurso de drawable para la marca específica ni "nologo"
        }
    }
    fun obtenerColorTexto(colorFondoHex: String): String {
        val colorFondo = Color.parseColor(colorFondoHex)
        val luminanciaFondo = (0.299 * Color.red(colorFondo) + 0.587 * Color.green(colorFondo) + 0.114 * Color.blue(colorFondo)) / 255
        return if (luminanciaFondo > 0.5) {
            // Si el fondo es claro, usa texto oscuro
            "#000000"
        } else {
            // Si el fondo es oscuro, usa texto claro
            "#FFFFFF"
        }
    }

}