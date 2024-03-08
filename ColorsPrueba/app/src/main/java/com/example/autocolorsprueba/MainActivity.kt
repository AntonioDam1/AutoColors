package com.example.autocolorsprueba

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.colorpickerview.AlphaTileView
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.colorpickerview.ColorPickerView
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import com.skydoves.colorpickerview.sliders.AlphaSlideBar
import com.skydoves.colorpickerview.sliders.BrightnessSlideBar
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View.OnClickListener
import androidx.core.view.forEach
import com.example.autocolorsprueba.database.CochesRoomDatabase
import com.example.autocolorsprueba.httpClient.HttpClient
import com.example.autocolorsprueba.model.entity.ColorCoche
import com.example.autocolorsprueba.model.entity.ColorFav
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), HttpClient.HttpClientListener {

    lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var colorPickerView: ColorPickerView
    private lateinit var alphaSlider: AlphaSlideBar
    private lateinit var brightnessSlideBar: BrightnessSlideBar
    private lateinit var alphaTileView: AlphaTileView
    private lateinit var textView: TextView

    // private lateinit var appToolbar: Toolbar
    private lateinit var itemCopiar: MenuItem
    private lateinit var itemAgregar: MenuItem
    private lateinit var itemFav: MenuItem
    private lateinit var itemComparar: MenuItem


    private lateinit var toolbar: Toolbar

    private var hexadecimal: String = ""

    private lateinit var httpClient: HttpClient
    private lateinit var serverUrl: String
    private lateinit var params: Map<String,String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorPickerView = findViewById(R.id.colorPickerView)
        alphaSlider = findViewById(R.id.alphaSlideBar)
        brightnessSlideBar = findViewById(R.id.brightnessSlide)
        alphaTileView = findViewById(R.id.alphaTileView)
        textView = findViewById(R.id.textView)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupBottomMenu()
        registerForContextMenu(alphaTileView)

        httpClient = HttpClient(this)
        serverUrl = "https://0467-176-12-82-226.ngrok-free.app/endpoint"
        params = mapOf("clave1" to "valor1", "clave2" to "valor2", "clave3" to "valor3")

        colorPickerView.setColorListener(object : ColorEnvelopeListener {
            override fun onColorSelected(envelope: ColorEnvelope, fromUser: Boolean) {
                alphaTileView.setPaintColor(envelope.color)
                textView.text = "#${envelope.hexCode}"
                hexadecimal = "#${envelope.hexCode}"

                toolbar.setBackgroundColor(envelope.color)
                bottomNavigationView.setBackgroundColor(envelope.color)


            }
        })

        colorPickerView.attachAlphaSlider(alphaSlider)
        colorPickerView.attachBrightnessSlider(brightnessSlideBar)
    }
    private fun onItemSelectedListener(item: MenuItem): Boolean {
        val itemId = item.itemId
        when (item.itemId) {
            R.id.galeria -> {
                val intent = Intent(this, galeria::class.java).apply {  }
                val b = Bundle()
                b.putString("hexadecimal",hexadecimal)
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


        override fun onCreateContextMenu(
            menu: ContextMenu,
            v: View,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            super.onCreateContextMenu(menu, v, menuInfo)
            menuInflater.inflate(R.menu.fav_menu, menu)
            itemCopiar = menu.findItem(R.id.agregarFavoritos)
            itemAgregar = menu.findItem(R.id.itemcopiar)
            itemComparar = menu.findItem(R.id.comparar)
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
                val intent = Intent(this@MainActivity, FavoritosActivity::class.java)
                // Iniciar la actividad
                startActivity(intent)
                // Devolver true para indicar que el evento ha sido consumido
                true
            }

            // Devolver true para mostrar el menú en la barra de acción.


            return true
        }

        override fun onContextItemSelected(item: MenuItem): Boolean {
            var i = 0
            when (item.itemId) {
                R.id.agregarFavoritos -> {
                    var color = hexadecimal
//                    var colorFav = ColorFav(0,
//                        "nombre de $color", 2024, "seat", "altea", color, "codigo color")
                    var database  = CochesRoomDatabase.getInstance(this)

                    GlobalScope.launch(Dispatchers.IO) {


                    }
                    showToast("Agregado a Favoritos")
                    return true
                }

                R.id.itemcopiar -> {

                    val textoACopiar = hexadecimal.toString()// Reemplaza esto con tu texto
                    copyToClipboard(textoACopiar)
                    showToast("Texto copiado al portapapeles")
                    return true
                }

                R.id.comparar -> {
                    httpClient.executeGetRequest(serverUrl, params)

//                    val httpClient = HttpClient(this)
//                    val intent = Intent(this@MainActivity, galeria::class.java).apply {
//                    }
//                    startActivity(intent)
                    return true
                }

                else -> return super.onContextItemSelected(item)
            }
        }
    override fun onResponseReceived(response: String) {
        // Maneja la respuesta del servidor aquí
        Log.d("Response", response)
    }

        private fun copyToClipboard(text: String) {
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("Texto copiado", text)
            clipboardManager.setPrimaryClip(clipData)
        }

        private fun showToast(message: String) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

    }





