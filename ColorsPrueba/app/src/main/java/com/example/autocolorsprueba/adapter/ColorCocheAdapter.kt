package com.example.autocolorsprueba.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.autocolorsprueba.ColorCocheDetail
import com.example.autocolorsprueba.R
import com.example.autocolorsprueba.database.CochesRoomDatabase
import com.example.autocolorsprueba.model.entity.ColorCoche
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DecimalFormat


/**
 * Adaptador para la lista con los resultados que .
 * Este adaptador se utiliza para mostrar la lista de colores de coches favoritos en un RecyclerView.
 *
 * @property colorCocheList La lista de colores de coches favoritos que se mostrará en el RecyclerView.
 * @return el recyclerView con los elementos de la lista
 */
class ColorCocheAdapter(val colorCocheList: MutableList<ColorCoche>) : RecyclerView.Adapter<ColorCocheViewHolder>() {


    /**
     * Crea y devuelve un nuevo ViewHolder.
     *
     * @param parent El ViewGroup al que se adjuntará el nuevo ViewHolder.
     * @param viewType El tipo de vista del nuevo ViewHolder.
     * @return El nuevo ViewHolder creado.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorCocheViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ColorCocheViewHolder(layoutInflater.inflate(R.layout.item_colorcoche, parent, false))
        TODO("Not yet implemented")
    }

    /**
     * Devuelve el número total de colores que hay en la lista.
     *
     * @return El número total de colores que hay en la lista.
     */
    override fun getItemCount(): Int = colorCocheList.size



    /**
     * Actualiza el contenido de un ViewHolder en una posición específica.
     *
     * @param holder El ViewHolder que debe actualizarse.
     * @param position La posición del elemento en la lista de datos.
     */
    override fun onBindViewHolder(holder: ColorCocheViewHolder, position: Int) {
        val coche : ColorCoche = colorCocheList[position]
        val tamano = colorCocheList.size
        val item = colorCocheList[position]
        holder.render(item)

        holder.itemView.setOnClickListener {

            val intent = Intent(it.context, ColorCocheDetail::class.java)
            intent.putExtra("origen","car")
            intent?.putExtra("marca", item.marca)
            intent?.putExtra("hexadecimal", item.hexadecimal)
            intent.putExtra("anio", item.anio.toString())
            intent.putExtra("modelo", item.modelo)
            intent.putExtra("nombrePintura", item.nombrePintura)
            intent.putExtra("codigo", item.codigo)
            intent?.putExtra("CATALOGO_URL", item.catalogueURL)
            intent?.putExtra("red", item.red.toString())
            intent?.putExtra("blue", item.blue.toString())
            intent?.putExtra("green", item.green.toString())
            intent?.putExtra("colorsample", item.colorsampleURL)
            intent?.putExtra("match", item.matchPercentage.toString())
//            intent.putExtra("coche", coche)
            //intent.putExtras(item)
            holder.itemView.context.startActivity(intent)

        }
    }
}

/**
 * Clase que modela el ViewHolder para un elemento de la lista de resultados colores de coches.
 * Este ViewHolder se utiliza para mostrar la información de un color de coche en un elemento de la lista del RecyclerView.
 *
 * @property view La vista que contiene los elementos de la interfaz de usuario para mostrar la información del color del coche.
 */
class ColorCocheViewHolder(view: View): RecyclerView.ViewHolder(view){
    val hexadecimal = view.findViewById<TextView>(R.id.tvHexadecimal)
    val marca = view.findViewById<TextView>(R.id.tvMarca)
    val codigo = view.findViewById<TextView>(R.id.tvCodigo)
    val match = view.findViewById<TextView>(R.id.tvMatch)
    val fondo = view.findViewById<ImageView>(R.id.colorin)
    val anio = view.findViewById<TextView>(R.id.tvAnio)
    val nombrePintura = view.findViewById<TextView>(R.id.tvNombrePintura)

    /**
     * Método para establecer los datos del color del coche en la interfaz de usuario.
     *
     * @param colorCoche El objeto ColorFav que contiene la información del color del coche favorito.
     */
    fun render(colorCoche: ColorCoche){
        hexadecimal.text = colorCoche.hexadecimal?.toUpperCase()
        nombrePintura.text = colorCoche.nombrePintura
        marca.text = colorCoche.marca
        codigo.text = colorCoche.codigo
        val formato = DecimalFormat("##.##' %'")
        val valorRedondeado = formato.format(100 - colorCoche.matchPercentage!!)
        match.text = valorRedondeado

        val textoAnio = if (colorCoche.anio != null && colorCoche.anio >= 1900) {
            colorCoche.anio
        } else {
            "N/A"
        }

        anio.text = textoAnio.toString()

        fondo.setBackgroundColor(Color.parseColor(colorCoche.hexadecimal))


    }

}