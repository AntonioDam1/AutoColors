package com.example.autocolorsprueba

import HttpClient
import android.app.Activity
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
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.forEach
import com.example.autocolorsprueba.database.CochesRoomDatabase
//import com.example.autocolorsprueba.httpClient.HttpClient
import com.example.autocolorsprueba.model.entity.ColorCoche
import com.example.autocolorsprueba.model.entity.ColorFav
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.FileNotFoundException

/**
 * Esta clase sirve para poder escoger un color de la rueda, de una foto de la galería o de nuestra cámara.
 * Se puede mover el selector por toda la rueda y de la barra para crear nuestro color.
 * Implementa la interfaz HttpClientListener
 * También compara el color escogido con los de la base de datos, haciendo una consulta en esta
 */

class MainActivity : AppCompatActivity(), HttpClient.HttpClientListener{
    //Navegación
    lateinit var bottomNavigationView: BottomNavigationView

    //ColorPickerView
    private lateinit var colorPickerView: ColorPickerView
    private lateinit var brightnessSlideBar: BrightnessSlideBar
    private lateinit var alphaTileView: AlphaTileView
    private lateinit var textView: TextView


    // private lateinit var appToolbar: Toolbar
    private lateinit var itemCopiar: MenuItem
    private lateinit var itemAgregar: MenuItem
    private lateinit var itemFav: MenuItem
    private lateinit var itemComparar: MenuItem


    //Toolbar
    private lateinit var toolbar: Toolbar

    private var hexadecimal: String = ""

    //Conexión con el servidor
    private lateinit var httpClient: HttpClient
    private lateinit var serverUrl: String
    private lateinit var params: Map<String,String>

    //Galeria
    val REQUEST_CODE_GALLERY = 200
    private lateinit var btnImage: Button

    //Cámara
    private lateinit var btnCamara: Button

   //Auxiliar

    /**
     *Método que se ejecuta al iniciar la clase. Se declaran las variables.
     * Se declara la url para hacer consultas al servidor.
     * Se declaran los listener del colorPicker y también del botón para abrir la galeria.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorPickerView = findViewById(R.id.colorPickerView)
        brightnessSlideBar = findViewById(R.id.brightnessSlide)
        alphaTileView = findViewById(R.id.alphaTileViewOriginal)
        textView = findViewById(R.id.textView)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupBottomMenu()
        registerForContextMenu(alphaTileView)

        //Conexión con el servidor
        httpClient = HttpClient(this)
        serverUrl = "https://ccd3-176-12-82-226.ngrok-free.app/endpoint"

        val database = CochesRoomDatabase.getInstance(this)
        val cocheMock = ColorCoche(
            uid = 1,
            anio = 2022,
            marca = "Toyota",
            modelo = "Corolla",
            nombrePintura = "Rojo Brillante",
            codigo = "RR123",
            catalogueURL = "https://example.com/catalogue",
            hexadecimal = "#FF0000",
            red = 255,
            green = 0,
            blue = 0,
            colorsampleURL = "https://example.com/colorsample",
            matchPercentage = 100.0
        )
        //Mock insert para que la bbdd despierte y este preparada. Estaria bien sustituirlo por arranque de la bbdd bien
        GlobalScope.launch(Dispatchers.IO) {
            database.colorCocheDao().insert(cocheMock)
            database.colorCocheDao().deleteAll()
        }


        colorPickerView.setColorListener(object : ColorEnvelopeListener {
            override fun onColorSelected(envelope: ColorEnvelope, fromUser: Boolean) {
                textView.text = "#${envelope.hexCode}"
                hexadecimal = "#${envelope.hexCode}"
                println(hexadecimal)

                toolbar.setBackgroundColor(envelope.color)
                bottomNavigationView.setBackgroundColor(envelope.color)


            }
        })
        colorPickerView.attachBrightnessSlider(brightnessSlideBar)


        /**
         * Listener para abrir la galeria
         */
        btnImage = findViewById(R.id.botonImagen)
        btnImage.setOnClickListener {
            abrirGaleria()
        }

        /**
         * Listener para abrir la cámara
         */
        btnCamara = findViewById(R.id.botonCamara)
        btnCamara.setOnClickListener{
            startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }

        /**
         * Listener para el colorPicker.
         * Al mover el selector de la paleta este va a cambiar el código hexadecimal del color y también el color de la
         * toolbar y la barra de navegación
         * Guarda tambien el color en hexadecimal para su posterior uso
         */
        colorPickerView.setColorListener(object : ColorEnvelopeListener {
            override fun onColorSelected(envelope: ColorEnvelope, fromUser: Boolean) {
                var hexa = envelope.getHexCode()
                hexa = "#" + hexa.substring(2,hexa.length)
                alphaTileView.setPaintColor(envelope.color)
                textView.text = hexa
                hexadecimal = "${hexa}"

                toolbar.setBackgroundColor(envelope.color)
                bottomNavigationView.setBackgroundColor(envelope.color)


            }
        })
    }

    /**
     * Método para abrir la galeria del móvil. Este método separa las versiones del sdk del dispositivo para abrir la galeria de una forma
     * u otra. Se deja preparado para la selección múltiple de fotos pero no se usa.
     * El método abre la galeria pero los resultados se procesan en el método onActivityResult
     */

    private fun abrirGaleria(){
        if (Build.VERSION.SDK_INT < 19) {
            var intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Choose Pictures"), REQUEST_CODE_GALLERY
            )
        } else { // For latest versions API LEVEL 19+
            var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE_GALLERY);
        }

    }

    /**
     * Función para abrir la cámara. Si todo va bien convierte esa foto en un mapa de bit y lo pone como paleta para escoger color
     */
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val imageBitmap = intent?.extras?.get("data") as Bitmap
                val drawable: Drawable = BitmapDrawable(resources, imageBitmap)
                colorPickerView.setPaletteDrawable(drawable)
            }
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
                b.putString("hexadecimal",hexadecimal)
                intent.putExtras(b  )
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
     * Método para la gestión de la foto escogida de la galeria. Si se ha escogido foto se asigna a una variable de tipo Uri
     * para posteriormente pasarla a mapa de bit y ponerla como paleta
     * @param resultCode código de resultado
     * @param data la imagen escogida
     * @param requestCode código de solicitud de acción
     *
     * @throws una excepción si hay error al pasar la imagen a mapa de bits
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_GALLERY){

            // if multiple images are selected
            if (data?.getClipData() != null) {
                var count = data.clipData?.itemCount

                for (i in 0..count!! - 1) {
                    var imageUri: Uri = data.clipData?.getItemAt(i)!!.uri
                    //     iv_image.setImageURI(imageUri) Here you can assign your Image URI to the ImageViews
                }

            } else if (data?.getData() != null) {
                // if single image is selected
                try {
                    var imageUri: Uri = data.data!!
                    val imageStream = contentResolver.openInputStream(imageUri)
                    val selectedImage = BitmapFactory.decodeStream(imageStream)
                    val drawable: Drawable = BitmapDrawable(resources, selectedImage)
                    colorPickerView.setPaletteDrawable(drawable)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }


                var imageUri: Uri = data.data!!

            }
        }
    }

    /**
     * Función para inflar la barra de navegación.
     * Se le asigna un listener a cada item.
     * Se pone seleccionado el item selector por defecto
     */

    private fun setupBottomMenu() {

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.selector

        bottomNavigationView.setOnItemSelectedListener { item -> onItemSelectedListener(item) }


    }


    /**
     * Método para inflar el menú de el AlphaTileView.
     * @param menu el menu a inflar
     * @param v la view
     * @param menuInfo
     */
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

    /**
     * Método para inflar el menú de la toolbar, que nos llevará a favoritos
     * @param menu El menú en el que se inflarán los elementos de la barra de herramientas.
     * @return Devuelve true para mostrar el menú en la barra de herramientas, false de lo contrario.
     */
        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.menu_toolbar, menu)
            itemFav = menu.findItem(R.id.corazon)
            // Asignar un click listener al ítem de menú corazón
            itemFav.setOnMenuItemClickListener {

                ColorStorage.setString(hexadecimal)
                // Crear un Intent para iniciar la actividad de Galería
                val intent = Intent(this@MainActivity, FavoritosActivity::class.java)
                // Iniciar la actividad
                startActivity(intent)
                // Devolver true para indicar que el evento ha sido consumido
                  true
            }


            return true
        }

    /**
     * Método para manejar las acciones del menú del alphaTileView.
     * Si le damos a agregar a favoritos, nos inserta el color en la base de datos de los colores favoritos
     * Si le damos a copiar hexadecimal nos copiará el hexadecimal al portapapeles
     * Si le damos a comparar, nos comparará el color con los de la base de datos
     * Cada acción tiene un toast, que mostrará un mensaje
     * @param item el item para realizar la acción
     * @return boolean. Devolvemos true si ha ido bien
     */

    override fun onContextItemSelected(item: MenuItem): Boolean {
            var i = 0
            when (item.itemId) {
                R.id.agregarFavoritos -> {
                    var colorCoche = ColorFav(0,0,"","", "","",
                        "",hexadecimal,0,0,0, "",0.0)
                    var database  = CochesRoomDatabase.getInstance(this)

                    GlobalScope.launch(Dispatchers.IO) {
                        database.colorFavDao().insert(colorCoche)
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

                    params = mapOf(
                        "color" to hexadecimal.substring(1),
                        "marca" to "",
                        "año" to "",
                        "match" to "93"
                    )

                    httpClient.executeGetRequest(serverUrl, params)

                    return true

                }

                else -> return super.onContextItemSelected(item)
            }
        }

    /**
     * Función que se tiene que implementar de la interfaz HttpClientListener
     * Nos creamos una instancia de la base de datos
     * Este método trata los objetos del JSON que viene de la consulta con la base de datos.
     * Primero borramos lo que haya en la base de datos, para así borrar los resultados de consultas prevías en un hilo aparte
     * Después recorremos la lista para insertar el objeto en nuestra base de datos para los resultados de las consultas
     * Insertamos los coches en un hilo diferente. Nos guardamos el color a comparar para mostrarlo después
     * Por último nos manda a ConsultasActivity para ver el resultado de la consulta
     * @param cochesList recibe la lista de coches que devuelve la base de datos
     */
    override fun onCochesReceived(cochesList: List<HttpClient.Coches>){
        val uniqueEntries = mutableSetOf<Pair<String, String>>() // Usamos un conjunto de pares (hexadecimal, marca) para mantener únicos los registros
        var database = CochesRoomDatabase.getInstance(this)

        GlobalScope.launch(Dispatchers.IO) {
            database.colorCocheDao().deleteAll()
        }
        if (cochesList.size == 0) {
            showToast("No hay resultados para ese color")
        } else {
            for (coche in cochesList) {
            Log.d("Coche", coche.toString())
                var colorCoche  = ColorCoche(coche.id, coche.year, coche.maker, coche.model, coche.paintColorName,
                    coche.code, coche.catalogueURL,coche.hexadecimal,coche.red,coche.green, coche.blue, coche.colorSampleURL, coche.matchPercentage)

                val pair = Pair(coche.hexadecimal, coche.maker)
                if (pair !in uniqueEntries) { // Comprobamos si la combinación hexadecimal-marca ya está en la lista
                    uniqueEntries.add(pair) // Agregamos la combinación al conjunto para evitar duplicados
                    val colorCoche = ColorCoche(
                        coche.id,
                        coche.year,
                        coche.maker,
                        coche.model,
                        coche.paintColorName,
                        coche.code,
                        coche.catalogueURL,
                        coche.hexadecimal,
                        coche.red,
                        coche.green,
                        coche.blue,
                        coche.colorSampleURL,
                        coche.matchPercentage
                    )
//                    Log.d("COLOR-COCHE", colorCoche.toString())

                    GlobalScope.launch(Dispatchers.IO) {
                        database.colorCocheDao().insert(colorCoche)
                    }
                }
            }

            ColorStorage.setString(hexadecimal.substring(1))
            val intent = Intent(this@MainActivity, ConsultasActivity::class.java)


            startActivity(intent)
        }

    }


    /**
     * Función para copiar al portapapeles el color
     * @param text el color a copiar
     * Nos saca un mensaje de aviso de que se ha copiado
     */
    private fun copyToClipboard(text: String) {
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("Texto copiado", text)
            clipboardManager.setPrimaryClip(clipData)
        }

    /**
     * Función para sacar un Toast por pantalla
     * @param message el texto a sacar en el toat
     */
        private fun showToast(message: String) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

    }





