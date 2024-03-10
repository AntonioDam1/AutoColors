package com.example.autocolorsprueba

import HttpClient
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView

class Filtrar : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var itemFav: MenuItem
    private lateinit var hexadecimal : EditText
    private lateinit var marca : EditText
    private lateinit var year : EditText

    private lateinit var buttonBuscar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtrar)
        marca = findViewById(R.id.editTextMarcaCoche)
        year = findViewById(R.id.editTextDate)
        hexadecimal = findViewById(R.id.editTextHexadecimal)
        buttonBuscar = findViewById(R.id.buttonBuscar)
        buttonBuscar.setOnClickListener{
            buscarColor()
        }
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupBottomMenu()
    }

    private fun buscarColor() {
        val intent = Intent(this@Filtrar, ConsultasActivity::class.java).apply {

        }
//        val params = mutableMapOf<String, String>()
//        if (!hexadecimal.text.toString().isEmpty()){
//            params["HEXADECIMAL"] = hexadecimal.text.toString()
//
//        }
//        if (!year.text.toString().isEmpty()){
//            params["AÑO"] = hexadecimal.text.toString()
//        }
//        if (!marca.text.toString().isEmpty()){
//            params["MARCA"] = marca.text.toString()
//        }
//        val consulta = HttpClient("localhost",params, this )
//        consulta.executeGetRequest()

        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        itemFav = menu.findItem(R.id.corazon)
        // Asignar un click listener al ítem de menú corazón
        itemFav.setOnMenuItemClickListener {
//                var colorCoche = ColorCoche(0,"rojo",2023,"seat", "altea", "FFFFFF", "X122d")
//                var database  = CochesRoomDatabase.getInstance(this)
//
//                GlobalScope.launch(Dispatchers.IO) {
//                    database.colorCocheDao().insertAll(colorCoche)
//                }

            // Crear un Intent para iniciar la actividad de Galería
            val intent = Intent(this@Filtrar, FavoritosActivity::class.java)
            // Iniciar la actividad
            startActivity(intent)
            // Devolver true para indicar que el evento ha sido consumido
            true
        }

        // Devolver true para mostrar el menú en la barra de acción.


        return true
    }

    private fun setupBottomMenu() {

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.filtrar

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

                startActivity(intent)
            }
            R.id.selector -> {
                val intent = Intent(this, MainActivity::class.java)

                startActivity(intent)
            }

//            R.id. page_fav -> {
//                showPageFragment(R.drawable. ic_fav, R.string. bottom_nav_fav)
//                return true
//            }
        }
        return true
    }
}