package com.example.autocolorsprueba

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.autocolorsprueba.database.CochesRoomDatabase
import com.example.autocolorsprueba.model.entity.ColorFav
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Clase para mostrar lso detalles del color al que se lñe ha dado click en el recyclerview
 * Se puede acceder a esta clase desde la lista de favoritos o cuando se hace una consulta
 * Si venimos de favoritos no se va a comparar con el color escogido ya que eso no existe
 * pero sí que saldrá si accedemos desde los resultados de filtrar o en la pantalla principal al
 * comparar con la base de datos
 */
class ColorCocheDetail : AppCompatActivity() {
    private lateinit var botonBorrar: Button
    private lateinit var botonFav: Button
    private lateinit var toolbar: Toolbar
    private lateinit var alphaTileViewOriginal: ImageView
    private lateinit var alphaTileViewColor: ImageView
    private lateinit var tvMarcaDetail: TextView
    //private lateinit var textViewMarcaDetail: TextView
    private lateinit var textViewModeloDetail: TextView
    private lateinit var textViewAnioDetail: TextView
    private lateinit var textViewMatchDetail: TextView
    private lateinit var textViewColorOriginal: TextView
    private lateinit var textHexadecimalDetail: TextView
    private lateinit var textHexadecimalOriginalDetail : TextView
    private lateinit var tvNombrePinturaDetail : TextView
    private lateinit var textLogoMarca : ImageView

    lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var colorOriginal: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_coche_detail)

        //textViewMarcaDetail = findViewById(R.id.textViewMarcaDetail)
        textViewModeloDetail = findViewById(R.id.textViewModeloDetail)
        textViewAnioDetail = findViewById(R.id.textViewAnioDetail)
        textViewMatchDetail = findViewById(R.id.textViewMatchDetail)
        textViewColorOriginal = findViewById(R.id.textViewColorOriginal)
        textHexadecimalDetail = findViewById(R.id.tvHexadecimalDetail)
        textHexadecimalOriginalDetail = findViewById(R.id.tvHexadecimalOriginalDetail)
        tvNombrePinturaDetail = findViewById(R.id.tvNombrePinturaDetail)
        tvMarcaDetail = findViewById(R.id.tvMarcaDetail)
        textLogoMarca = findViewById(R.id.logo)

        val origen = intent.getStringExtra("origen")
        val marca = intent.getStringExtra("marca")
        val hexadecimal = intent.getStringExtra("hexadecimal")
        val modelo = intent.getStringExtra("modelo")
        val anio = intent.getStringExtra("anio")
        val match = intent.getStringExtra("match")
        val nombrePintura = intent.getStringExtra("nombrePintura")

        val marca_logo = intent.getStringExtra("marca")?.toLowerCase()
        val resourceId = if (marca_logo != null) {
            resources.getIdentifier("$marca_logo", "drawable", packageName)
        } else {
            resources.getIdentifier("nologo", "drawable", packageName)
        }

        if (resourceId != 0) {
            val svgUri = Uri.parse("android.resource://$packageName/$resourceId")
            textLogoMarca.setImageURI(svgUri)
        } else {
            // Manejar el caso en el que no se encuentre el recurso SVG
        }

        //textViewMarcaDetail.text = marca
        textViewModeloDetail.text = modelo ?: "N/A"

        val anioComoEntero = anio?.toIntOrNull()
        val textoAnio = if (anioComoEntero != null && anioComoEntero >= 1900) {
            anio
        } else {
            "N/A"
        }
        textViewAnioDetail.text = textoAnio

        tvMarcaDetail.text = marca ?: "N/A"
        tvNombrePinturaDetail = findViewById(R.id.tvNombrePinturaDetail)
        val codigo = intent.getStringExtra("codigo")
        tvNombrePinturaDetail.text = "$nombrePintura\n$codigo"

        textHexadecimalDetail.text = hexadecimal?.toUpperCase()
        textHexadecimalOriginalDetail.text = "#" + ColorStorage.getString()

        botonBorrar = findViewById(R.id.buttonBorrar)
        botonFav = findViewById(R.id.buttonFav)

        if (origen.equals("fav")){
            textViewModeloDetail.text = intent.getStringExtra("modelo") ?: "N/A"
            textViewMatchDetail.isEnabled = false
            textViewMatchDetail.visibility = View.INVISIBLE

            textHexadecimalOriginalDetail.isEnabled = false
            textHexadecimalOriginalDetail.visibility = View.INVISIBLE

            botonFav.isEnabled = false
            botonFav.visibility = View.INVISIBLE

        }
        if (origen.equals("car")) {
            botonBorrar.isEnabled = false
            botonBorrar.visibility = View.INVISIBLE

            textViewMatchDetail.text = "Match: ${String.format("%.2f", 100 - match!!.toDouble())} %"

        }



        alphaTileViewColor = findViewById(R.id.alphaTileViewColor)
        alphaTileViewOriginal  =findViewById(R.id.alphaTileViewOriginal)


        if (hexadecimal != null) {
            alphaTileViewColor.setBackgroundColor(Color.parseColor(hexadecimal))
            var savedString = ColorStorage.getString()
            if (savedString != null) {
                if (savedString.startsWith("#")) {
                    savedString = "#FF" + savedString.substring(1)
                }else{
                    savedString = "#FF" + savedString
                }
            }
            if (origen.equals("car")) {
                alphaTileViewOriginal.setBackgroundColor(Color.parseColor(savedString))
            } else {
                alphaTileViewOriginal.isEnabled = false
                alphaTileViewOriginal.visibility = View.INVISIBLE

                textViewColorOriginal.isEnabled = false
                textViewColorOriginal.visibility = View.INVISIBLE

            }
        }

        /*
        //Por si queréis bordes en los dos cuadrados de color.
        //
        // Crear un drawable en capas con la imagen y el borde
        val borderSize = 4// Tamaño del borde en píxeles
        val borderColor = Color.BLACK // Color del borde, puedes cambiarlo según tus preferencias
        val borderDrawable = GradientDrawable()
        borderDrawable.shape = GradientDrawable.RECTANGLE
        borderDrawable.setStroke(borderSize, borderColor)
        val layers = arrayOf(borderDrawable, alphaTileViewColor.drawable)
        val layers2 = arrayOf(borderDrawable, alphaTileViewOriginal.drawable)

        // Crear un nuevo drawable en capas con el borde y la imagen
        val layerDrawable = LayerDrawable(layers)
        val layerDrawable2 = LayerDrawable(layers2)

        // Establecer el nuevo drawable en capas como fuente de la imagen
        alphaTileViewColor.setImageDrawable(layerDrawable)
        alphaTileViewOriginal.setImageDrawable(layerDrawable2)
        */


        setupBottomMenu()

        botonBorrar.setOnClickListener {
            borrarColor()
        }
        botonFav.setOnClickListener {
            agregarFavoritos()
        }
    }

    /**
     * Función para agregar un color de coche que aparece en una consulta a la lista de favoritos.
     * Recupera los detalles del color de coche de los extras pasados a través del Intent.
     * Crea un objeto [ColorFav] con los detalles recuperados y lo inserta en la base de datos de forma asincrónica.
     * Muestra un mensaje de notificación al usuario para confirmar que el color de coche se ha agregado a favoritos.
     */
    private fun agregarFavoritos(){

        val hexadecimal = intent.getStringExtra("hexadecimal")
        val anio = intent.getStringExtra("anio")
        val marca = intent.getStringExtra("marca")
        val modelo = intent.getStringExtra("modelo")
        val nombrePintura = intent.getStringExtra("nombrePintura")
        val codigo = intent.getStringExtra("codigo")
        val catalog = intent.getStringExtra("CATALOGO_URL")
        val red = intent.getIntExtra("red",0)
        val green = intent.getIntExtra("green",0)
        val blue = intent.getIntExtra("blue",0)
        val colorsample = intent.getStringExtra("colorsample")
        val match = intent.getStringExtra("match")


         var colorCoche = ColorFav(0, anio?.toInt(),marca,modelo, nombrePintura,codigo,
             catalog,hexadecimal,red,green,blue, colorsample, match?.toDouble())
         var database  = CochesRoomDatabase.getInstance(this)

         GlobalScope.launch(Dispatchers.IO) {
             database.colorFavDao().insert(colorCoche)
         }


         showToast("Agregado a Favoritos")
    }



    /**
     * Elimina el color de coche seleccionado de la lista de favoritos en la base de datos.
     * Recupera el objeto ColorFav que se va a eliminar de los extras pasados a través del Intent.
     * Realiza una consulta a la base de datos para obtener todos los colores favoritos.
     * Elimina el color de coche de la lista y lo borra de la base de datos de forma asincrónica.
     * Finaliza la actividad actual y abre la actividad de favoritos para mostrar la lista actualizada.
     * Muestra un mensaje de notificación al usuario para confirmar que el color de coche ha sido borrado.
     */
    private fun borrarColor() {
        val item = intent.getSerializableExtra("ITEM_KEY") as ColorFav
        var database = CochesRoomDatabase.getInstance(this)
        var coloresFavs: MutableList<ColorFav>
        GlobalScope.launch(Dispatchers.IO) {
            coloresFavs = database.colorFavDao().getAll()
            coloresFavs.remove(item)
            database.colorFavDao().delete(item)
        }
        finish()
        val intent = Intent(this, FavoritosActivity::class.java)
        startActivity(intent)
        showToast("Color borrado")
    }


    /**
     * Función para inflar la barra de navegación.
     * Se le asigna un listener a cada item.
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
                val intent = Intent(this, ColorMaps::class.java).apply { }
                val b = Bundle()
                intent.putExtras(b)
                startActivity(intent)
            }
            R.id.selector -> {
                val intent = Intent(this, MainActivity::class.java)

                startActivity(intent)
            }

            R.id.filtrar -> {
                val intent = Intent(this, Filtrar::class.java).apply { }
                startActivity(intent)
            }

        }
        return true
    }

    /**
     * Función para copiar al portapapeles el color
     * @param text el color a copiar
     * Nos saca un mensaje de aviso de que se ha copiado
     */
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
