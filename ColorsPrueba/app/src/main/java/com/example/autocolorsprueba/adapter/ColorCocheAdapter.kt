package com.example.autocolorsprueba.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.autocolorsprueba.R
import com.example.autocolorsprueba.model.entity.ColorCoche
import org.w3c.dom.Text

class ColorCocheAdapter(val colorCocheList: List<ColorCoche> ) : RecyclerView.Adapter<ColorCocheViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorCocheViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ColorCocheViewHolder(layoutInflater.inflate(R.layout.item_colorcoche, parent, false))
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = colorCocheList.size


    override fun onBindViewHolder(holder: ColorCocheViewHolder, position: Int) {
        val item = colorCocheList[position]
        holder.render(item)
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
        nombre.text = colorCoche.nombre
        codigo.text = colorCoche.codigo + ", " + colorCoche.modelo
        fondo.setBackgroundColor(Color.parseColor(colorCoche.hexadecimal))


    }

}