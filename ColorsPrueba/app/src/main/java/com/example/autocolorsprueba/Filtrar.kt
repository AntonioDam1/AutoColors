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
import android.widget.Toast
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

        GlobalScope.launch(Dispatchers.IO) {
            database.colorCocheDao().deleteAll()
        }
        if (cochesList.size == 0) {
            showToast("No hay resultados para ese color, año o marca")
        }else{
            for (coche in cochesList) {
                Log.d("Coche", coche.toString())
                var colorCoche  = ColorCoche(coche.id, coche.year, coche.maker, coche.model, coche.paintColorName,
                    coche.code, coche.catalogueURL,coche.hexadecimal,coche.red,coche.green, coche.blue, coche.colorSampleURL, coche.matchPercentage)
                Log.d("COLOR-COCHE", colorCoche.toString())
                ColorStorage.setString(hexadecimal.text.toString().trim())

                GlobalScope.launch(Dispatchers.IO) {
                    database.colorCocheDao().insert(colorCoche)
                }
            }

            val intent = Intent(this@Filtrar, ConsultasActivity::class.java)

            startActivity(intent)
        }

    }

    private fun buscarColor() {
        val colorHex = hexadecimal.text.toString().trim()
        val marcaText = marca.text.toString().trim()
        val yearText = year.text.toString().trim()
        val matchText = match.text.toString().trim()

        val errores = mutableListOf<String>()

        if (!isValidColorHex(colorHex)) {
            errores.add("Formato de color hexadecimal incorrecto")
        }

        if (marcaText.isNotBlank() && !isValidMarca(marcaText)) {
            errores.add("Marca no válida. Marcas válidas: BMW, Ford, Lincoln, Acura, Honda, Mitsubishi, Sea, Volkswagen")
        }

        if (yearText.isNotBlank() && !isValidYear(yearText)) {
            errores.add("Formato de año incorrecto (deben ser 4 dígitos numéricos)")
        }

        if (matchText.isNotBlank() && !isValidMatch(matchText)) {
            errores.add("Porcentaje de coincidencia no válido (debe estar entre 0 y 100)")
        }

        if (errores.isNotEmpty()) {
            // Mostrar todos los mensajes de error acumulados
            for (error in errores) {
                showToast(error)
            }
        } else {
            // Todos los campos tienen valores válidos, puedes realizar la búsqueda
            val params = mapOf(
                "color" to colorHex,
                "marca" to marcaText,
                "año" to yearText,
                "match" to matchText
            )
            httpClient.executeGetRequest(serverUrl, params)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

// Resto de las funciones de validación sin cambios


    private fun isValidColorHex(colorHex: String): Boolean {
        val hexRegex = Regex("^#?([0-9a-fA-F]{6})$")
        return hexRegex.matches(colorHex)
    }

    private fun isValidMarca(marca: String): Boolean {
        val marcasValidas = setOf("BMW", "Ford", "Lincoln", "Acura", "Honda", "Mitsubishi", "Sea", "Volkswagen")
        return marca.isNotBlank() && marcasValidas.contains(marca)
    }

    private fun isValidYear(year: String): Boolean {
        return year.matches(Regex("\\d{4}"))
    }

    private fun isValidMatch(match: String): Boolean {
        return try {
            val matchValue = match.toDouble()
            matchValue >= 0 && matchValue <= 100
        } catch (e: NumberFormatException) {
            false
        }
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