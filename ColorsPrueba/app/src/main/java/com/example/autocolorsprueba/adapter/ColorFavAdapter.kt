package com.example.autocolorsprueba.adapter

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
    val fondo = view.findViewById<TextView>(R.id.colorin)
    val anio = view.findViewById<TextView>(R.id.tvAnio)

    /**
     * Método para establecer los datos del color del coche favorito en la interfaz de usuario.
     *
     * @param colorCoche El objeto ColorFav que contiene la información del color del coche favorito.
     */
    fun render(colorCoche: ColorFav){
        hexadecimal.text = colorCoche.hexadecimal ?: "N/A"
        marca.text = colorCoche.marca ?: "N/A"
        codigo.text = colorCoche.codigo + ", " + colorCoche.modelo
        match.text = colorCoche.matchPercentage.toString()
        anio.text = colorCoche.anio.toString()
        fondo.setBackgroundColor(Color.parseColor(colorCoche.hexadecimal))
    }

}