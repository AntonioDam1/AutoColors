package com.example.autocolorsprueba.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.autocolorsprueba.R
import com.example.autocolorsprueba.database.CochesRoomDatabase
import com.example.autocolorsprueba.model.entity.ColorCoche
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ColorCocheAdapter(val colorCocheList: MutableList<ColorCoche> ) : RecyclerView.Adapter<ColorCocheViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorCocheViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ColorCocheViewHolder(layoutInflater.inflate(R.layout.item_colorcoche, parent, false))
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = colorCocheList.size


    override fun onBindViewHolder(holder: ColorCocheViewHolder, position: Int) {
        val tamano = colorCocheList.size
        val item = colorCocheList[position]
        holder.render(item)

        holder.itemView.setOnClickListener {
//            // Llama a la función que maneja la eliminación del elemento
            eliminarElemento(item, holder, position)

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
    val nombre = view.findViewById<TextView>(R.id.tvNombre)
    val codigo = view.findViewById<TextView>(R.id.tvCodigo)
//    val match = view.findViewById<TextView>(R.id.tvMatch)
    val fondo = view.findViewById<TextView>(R.id.colorCoche)

    fun render(colorCoche: ColorCoche){
        hexadecimal.text = colorCoche.hexadecimal
//        nombre.text = colorCoche.nombre
//        codigo.text = colorCoche.codigo + ", " + colorCoche.modelo
        fondo.setBackgroundColor(Color.parseColor(colorCoche.hexadecimal))


    }

}