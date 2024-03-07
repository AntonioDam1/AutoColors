package com.example.autocolorsprueba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView

class Filtrar : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var itemFav: MenuItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtrar)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupBottomMenu()
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
            R.id.galeria -> {
                val intent = Intent(this, galeria::class.java).apply {  }
                val b = Bundle()

                startActivity(intent)
            }
            R.id.selector -> {
                val intent = Intent(this, MainActivity::class.java).apply {  }

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