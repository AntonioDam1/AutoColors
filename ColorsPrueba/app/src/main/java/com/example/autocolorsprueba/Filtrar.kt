package com.example.autocolorsprueba

import HttpClient
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import com.example.autocolorsprueba.database.CochesRoomDatabase
import com.example.autocolorsprueba.model.entity.ColorCoche
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Filtrar : AppCompatActivity(), HttpClient.HttpClientListener {
    lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var itemFav: MenuItem
    private lateinit var hexadecimal : EditText
    private lateinit var marca : EditText
    private lateinit var year : EditText
    private lateinit var match : EditText


    //Para la peticion al servidor
    private lateinit var httpClient: HttpClient
    private lateinit var serverUrl: String
    private lateinit var params: Map<String,String>

    private lateinit var buttonBuscar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtrar)
        marca = findViewById(R.id.editTextMarcaCoche)
        year = findViewById(R.id.editTextYear)
        hexadecimal = findViewById(R.id.editTextHexadecimal)
        match = findViewById(R.id.editTextMatch)

        //Para la peticion al servidor
        httpClient = HttpClient(this)
        serverUrl = "https://52ad-176-12-82-226.ngrok-free.app/endpoint"

        buttonBuscar = findViewById(R.id.buttonBuscar)
        buttonBuscar.setOnClickListener{
            buscarColor()
        }
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupBottomMenu()
    }

    override fun onCochesReceived(cochesList: List<HttpClient.Coches>){
        var database = CochesRoomDatabase.getInstance(this)
        for (coche in cochesList) {
            Log.d("Coche", coche.toString())
            var colorCoche  = ColorCoche(coche.id, coche.year, coche.maker, coche.model, coche.paintColorName,
                coche.code, coche.catalogueURL,coche.hexadecimal,coche.red,coche.green, coche.blue, coche.colorSampleURL, coche.matchPercentage)
            Log.d("COLOR-COCHE", colorCoche.toString())


            GlobalScope.launch(Dispatchers.IO) {
                database.colorCocheDao().insert(colorCoche)
            }
        }
        val intent = Intent(this@Filtrar, ConsultasActivity::class.java)

        startActivity(intent)
    }

    private fun buscarColor() {

        params = mapOf(
            "color" to hexadecimal.text.toString(),
            "marca" to marca.text.toString(),
            "año" to hexadecimal.text.toString(),
            "match" to match.text.toString()
        )
        httpClient.executeGetRequest(serverUrl, params)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        itemFav = menu.findItem(R.id.corazon)
        // Asignar un click listener al ítem de menú corazón
        itemFav.setOnMenuItemClickListener {

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