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
import com.example.autocolorsprueba.model.entity.ColorFav
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ColorFavAdapter(val colorFavList: MutableList<ColorFav> ) : RecyclerView.Adapter<ColorFavViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorFavViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ColorFavViewHolder(layoutInflater.inflate(R.layout.item_colorcoche, parent, false))
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = colorFavList.size


    override fun onBindViewHolder(holder: ColorFavViewHolder, position: Int) {
        val tamano = colorFavList.size
        val item = colorFavList[position]
        holder.render(item)

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, ColorCocheDetail::class.java)
            intent.putExtra("nombre", item.nombre)
            intent.putExtra("hexadecimal", item.hexadecimal)
            intent.putExtras(item)
        }
    }

//    private fun eliminarElemento(colorFav: ColorFav, holder: ColorFavViewHolder, position: Int) {
//        val database = CochesRoomDatabase.getInstance(holder.itemView.context)
//
//        // Utiliza coroutines para realizar operaciones en el hilo de trabajo
//        GlobalScope.launch(Dispatchers.IO) {
////            // Llama al método para eliminar el colorCoche de la base de datos
//            database.colorCocheDao().delete(colorCoche)
//            colorCocheList.removeAt(position)
//
//            withContext(Dispatchers.Main) {
//                println(colorCocheList.size)
//                // Notifica al adaptador sobre el cambio en los datos
//                notifyItemRemoved(position)
//
//
//            }
//
//        }
//
//
//    }
}

class ColorFavViewHolder(view: View): RecyclerView.ViewHolder(view){
    val hexadecimal = view.findViewById<TextView>(R.id.tvHexadecimal)
    val nombre = view.findViewById<TextView>(R.id.tvNombre)
    val codigo = view.findViewById<TextView>(R.id.tvCodigo)
    //    val match = view.findViewById<TextView>(R.id.tvMatch)
    val fondo = view.findViewById<TextView>(R.id.colorCoche)



    fun render(colorFav: ColorFav){
        hexadecimal.text = colorFav.hexadecimal
        println(hexadecimal)
//        nombre.text = ColorFav.nombre
//        codigo.text = ColorFav.codigo + ", " + ColorFav.modelo
        fondo.setBackgroundColor(Color.parseColor(colorFav.hexadecimal))


    }

}