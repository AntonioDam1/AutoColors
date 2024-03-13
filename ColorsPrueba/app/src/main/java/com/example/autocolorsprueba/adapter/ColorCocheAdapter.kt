package com.example.autocolorsprueba.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorCocheViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ColorCocheViewHolder(layoutInflater.inflate(R.layout.item_colorcoche, parent, false))
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = colorCocheList.size


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
            intent?.putExtra("anio", item.anio.toString())
            intent?.putExtra("modelo", item.modelo)
            intent?.putExtra("nombrePintura", item.nombrePintura)
            intent?.putExtra("codigo", item.codigo)
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

    private fun eliminarElemento(colorCoche: ColorCoche, holder: ColorCocheViewHolder, position: Int) {
        val database = CochesRoomDatabase.getInstance(holder.itemView.context)

        // Utiliza coroutines para realizar operaciones en el hilo de trabajo
        GlobalScope.launch(Dispatchers.IO) {
//            // Llama al método para eliminar el colorCoche de la base de datos
            database.colorCocheDao().delete(colorCoche)
            colorCocheList.removeAt(position)

            withContext(Dispatchers.Main) {
                println(colorCocheList.size)
                // Notifica al adaptador sobre el cambio en los datos
                notifyItemRemoved(position)


            }

        }


    }
}

class ColorCocheViewHolder(view: View): RecyclerView.ViewHolder(view){
    val hexadecimal = view.findViewById<TextView>(R.id.tvHexadecimal)
    val marca = view.findViewById<TextView>(R.id.tvMarca)
    val codigo = view.findViewById<TextView>(R.id.tvCodigo)
    val match = view.findViewById<TextView>(R.id.tvMatch)
    val fondo = view.findViewById<TextView>(R.id.colorin)
    val anio = view.findViewById<TextView>(R.id.tvAnio)
    val nombrePintura = view.findViewById<TextView>(R.id.tvNombrePintura)

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