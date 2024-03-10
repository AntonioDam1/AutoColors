package com.example.autocolorsprueba

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import com.example.autocolorsprueba.adapter.ColorFavAdapter
import com.example.autocolorsprueba.database.CochesRoomDatabase
import com.example.autocolorsprueba.model.entity.ColorFav
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.skydoves.colorpickerview.AlphaTileView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ColorCocheDetail : AppCompatActivity() {
    private lateinit var botonBorrar : Button
    private lateinit var botonFav : Button
    private lateinit var toolbar: Toolbar
    private lateinit var alphaTileViewOriginal: ImageView
    private lateinit var alphaTileViewColor: ImageView
    private lateinit var tvNombreDetalle : TextView
    private lateinit var tvCodigoDetalle : TextView
    private lateinit var favoritosActivity : FavoritosActivity
    private lateinit var miAdaptador: ColorFavAdapter
    lateinit var bottomNavigationView: BottomNavigationView





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_coche_detail)

        val origen = intent.getStringExtra("origen")
        val marca = intent.getStringExtra("marca")
        val hexadecimal = intent.getStringExtra("hexadecimal")


        botonBorrar = findViewById(R.id.buttonBorrar)
        botonFav = findViewById(R.id.buttonFav)
        if (origen.equals("fav")){
            botonFav.isEnabled = false
        }

        toolbar = findViewById(R.id.toolbar)
        alphaTileViewColor = findViewById(R.id.alphaTileViewColor)
        alphaTileViewOriginal  =findViewById(R.id.alphaTileViewOriginal)

        if (hexadecimal != null) {
            alphaTileViewOriginal.setBackgroundColor(Color.parseColor(hexadecimal))
        }

        tvNombreDetalle = findViewById(R.id.tvNombreColorDetalle)
        tvNombreDetalle.text = marca
        tvCodigoDetalle = findViewById(R.id.tvCodigoDetalle)
        tvCodigoDetalle.text = hexadecimal
        setupBottomMenu()




        botonBorrar.setOnClickListener {
            borrarColor()
        }
        botonFav.setOnClickListener{
            //agregarFavoritos()
        }



    }

    private fun borrarColor(){


        val item = intent.getSerializableExtra("ITEM_KEY") as ColorFav


        var database  = CochesRoomDatabase.getInstance(this)
        var coloresFavs : MutableList<ColorFav>
        coloresFavs= database.colorFavDao().getAll()

        coloresFavs.remove(item)
        miAdaptador.notifyDataSetChanged()


    }

//    private fun agregarFavoritos(){
//        val hexadecimal = intent.getStringExtra("hexadecimal")
//
//        var colorFav = ColorFav(0,
//            "nombre de ", 2020, "seat",
//            "altea", hexadecimal, "","",
//            0,0,0,"")
//
//        var database  = CochesRoomDatabase.getInstance(this)
//
//        GlobalScope.launch(Dispatchers.IO) {
//            database.colorFavDao().insertAll(colorFav)
//
//
//        }
//        showToast("Agregado a Favoritos")
//
//    }

    private fun setupBottomMenu() {

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.selector

        bottomNavigationView.setOnItemSelectedListener { item -> onItemSelectedListener(item) }

        bottomNavigationView.menu.forEach { menuItem ->
            val badge = bottomNavigationView.getOrCreateBadge(menuItem.itemId)
            badge.isVisible = false
            badge.badgeGravity = BadgeDrawable.TOP_START
        }
    }

    private fun onItemSelectedListener(item: MenuItem): Boolean {
        val itemId = item.itemId
        when (item.itemId) {
            R.id.taller -> {
                val intent = Intent(this, ColorMaps::class.java).apply {  }
                val b = Bundle()
//                b.putString("hexadecimal",hexadecimal)
                intent.putExtras(b  )
                startActivity(intent)
            }
            R.id.filtrar -> {
                val intent = Intent(this, Filtrar::class.java).apply {  }
                startActivity(intent)
            }

//            R.id. page_fav -> {
//                showPageFragment(R.drawable. ic_fav, R.string. bottom_nav_fav)
//                return true
//            }
        }
        return true
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}