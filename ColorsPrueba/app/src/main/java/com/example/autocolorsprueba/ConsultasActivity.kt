package com.example.autocolorsprueba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autocolorsprueba.adapter.ColorCocheAdapter
import com.example.autocolorsprueba.database.CochesRoomDatabase
import com.example.autocolorsprueba.model.entity.ColorCoche
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * En esta clase vamos a iniciar el recyclerView para los colores que nos han dado de resultado
 * al hacer la consulta en filtrar
 */
class ConsultasActivity() : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var nombre : TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultas)
        recyclerView = findViewById(R.id.recyclerConsultas)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupBottomMenu()
        registerForContextMenu(recyclerView)
        initRecyclerView()


    }


    /**
     * Función para inflar la barra de navegación.
     * Se le asigna un listener a cada item.
     * Se pone seleccionado el item Filtrar por defecto
     */
    private fun setupBottomMenu() {

        bottomNavigationView = findViewById(R.id.bottom_navigation)

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
     * Método para iniciar el recyclerview de consultas y pasarle las lista necesaria
     * Cogemos los datos con un getAll en un hilo distinto
     * Le decimos al adapter que es esa lista la que debe meter
     * Le decimos que van a ir los resultados en un Linear Layout
     */
    fun initRecyclerView(){
        var database  = CochesRoomDatabase.getInstance(this)

        var cochesColores : MutableList<ColorCoche>

        GlobalScope.launch(Dispatchers.IO) {
            cochesColores= database.colorCocheDao().getAll()

            runOnUiThread {
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerConsultas)

                recyclerView.layoutManager = LinearLayoutManager(this@ConsultasActivity)
                recyclerView.adapter = ColorCocheAdapter(cochesColores)

            }
        }

    }
}