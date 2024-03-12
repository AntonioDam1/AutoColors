package com.example.autocolorsprueba

import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autocolorsprueba.adapter.ColorCocheAdapter
import com.example.autocolorsprueba.adapter.ColorFavAdapter
import com.example.autocolorsprueba.database.CochesRoomDatabase
import com.example.autocolorsprueba.model.entity.ColorCoche
import com.example.autocolorsprueba.model.entity.ColorFav
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.skydoves.colorpickerview.preference.ColorPickerPreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * Clase para ver el listado de los colores favortos ya insertado en Room.
 * Nos va a mostrar un recyclerView con los elementos.
 */
class FavoritosActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var itemBorra :MenuItem
    private lateinit var nombre : TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemFav: MenuItem



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)
        recyclerView = findViewById(R.id.recyclerFavs)
        toolbar = findViewById(R.id.toolbar)
//        nombre = findViewById(R.id.tvNombre)
        setSupportActionBar(toolbar)
        setupBottomMenu()
        registerForContextMenu(recyclerView)
        val manager = ColorPickerPreferenceManager.getInstance(this)

        manager.setSelectorPosition(
            "MyColorPicker",
            Point(20, 20)
        ) // manipulates the saved selector's position data.

        initRecyclerView()
    }



    /**
     * Función para inflar la barra de navegación.
     * Se le asigna un listener a cada item.
     * Se pone seleccionado el item Filtrar por defecto
     */
    private fun setupBottomMenu() {

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.selector

        bottomNavigationView.setOnItemSelectedListener { item -> onItemSelectedListener(item) }

    }

    /**
     * Función para la navegacion de la BottomNavigation.
     * @param item El elemento del menú que se ha seleccionado.
     * @return true para indicar que el evento ha sido consumido, false de lo contrario.
     */
    private fun onItemSelectedListener(item: MenuItem): Boolean {
        val itemId = item.itemId
        when (item.itemId) {
            R.id.taller -> {
                val intent = Intent(this, ColorMaps::class.java).apply {  }
                val b = Bundle()

                startActivity(intent)
            }
            R.id.selector -> {
                val intent = Intent(this, MainActivity::class.java).apply {  }

                startActivity(intent)
            }
            R.id.filtrar -> {
                val intent = Intent(this, Filtrar::class.java).apply {  }
                startActivity(intent)
            }

        }
        return true
    }

    /**
     * Método para iniciar el recyclerview de favoritos y pasarle las lista necesaria
     * Cogemos los datos con un getAll en un hilo distinto
     * Le decimos al adapter que es esa lista la que debe meter
     */
    fun initRecyclerView(){
        var database  = CochesRoomDatabase.getInstance(this)
        var coloresFavs : MutableList<ColorFav>
        GlobalScope.launch(Dispatchers.IO) {
            coloresFavs= database.colorFavDao().getAll()
            runOnUiThread {
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerFavs)

                recyclerView.layoutManager = LinearLayoutManager(this@FavoritosActivity)
                recyclerView.adapter = ColorFavAdapter(coloresFavs)

            }
        }

    }




}