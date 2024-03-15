    package com.example.autocolorsprueba

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
    import com.example.autocolorsprueba.httpClient.HttpClient
    import com.example.autocolorsprueba.model.entity.ColorCoche
    import com.google.android.material.badge.BadgeDrawable
    import com.google.android.material.bottomnavigation.BottomNavigationView
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.GlobalScope
    import kotlinx.coroutines.launch

    /**
     * Clase para poder filtrar los colores por hexadecimal, marca, año o porcentaje de match
     * Implementa la interfaz HttpClienListener
     * Lanza una consulta a la base de datos y nos trata los resultados para luego mandarnos al activity que los muestra
     */

    class Filtrar : AppCompatActivity(), HttpClient.HttpClientListener {
        private lateinit var bottomNavigationView: BottomNavigationView
        private lateinit var toolbar: Toolbar
        private lateinit var itemFav: MenuItem
        lateinit var hexadecimal : EditText
        lateinit var marca : EditText
        lateinit var year : EditText
        lateinit var match : EditText


        //Para la peticion al servidor
        private lateinit var httpClient: HttpClient
        lateinit var serverUrl: String
        private lateinit var params: Map<String,String>

        private lateinit var buttonBuscar : Button

        /**
         * Método que se ejecuta al iniciar la activity
         * Declaramos la url del servidor para hacer la consulta
         * establecemos los listeners del boton, el cual lanza la consulta
         */
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_filtrar)
            marca = findViewById(R.id.editTextMarcaCoche)
            year = findViewById(R.id.editTextYear)
            hexadecimal = findViewById(R.id.editTextHexadecimal)
            match = findViewById(R.id.editTextMatch)

            //Para la peticion al servidor
            httpClient = HttpClient(this)
            serverUrl = "https://ccd3-176-12-82-226.ngrok-free.app/endpoint"

            buttonBuscar = findViewById(R.id.buttonBuscar)
            buttonBuscar.setOnClickListener{
                buscarColor()
            }
            toolbar = findViewById(R.id.toolbar)
            setSupportActionBar(toolbar)
            setupBottomMenu()
        }

        /**
         * Función que se tiene que implementar de la interfaz HttpClientListener
         * Nos creamos una instancia de la base de datos
         * Este método trata los objetos del JSON que viene de la consulta con la base de datos.
         * Primero borramos lo que haya en la base de datos, para así borrar los resultados de consultas prevías en un hilo aparte
         * Después recorremos la lista para insertar el objeto en nuestra base de datos para los resultados de las consultas
         * Si no existe ese color para una marca lo  insertamos en un hilo diferente, para así no repetir los mismos colores.
         * Nos guardamos ese color para que al tocar un color de la lista lo comparemos con el original
         * Por último nos manda a ConsultasActivity para ver el resultado de la consulta
         * @param cochesList recibe la lista de coches que devuelve la base de datos
         */
        override fun onCochesReceived(cochesList: List<HttpClient.Coches>){
            var database = CochesRoomDatabase.getInstance(this)
            val uniqueEntries = mutableSetOf<Pair<String, String>>() // Usamos un conjunto de pares (hexadecimal, marca) para mantener únicos los registros

            GlobalScope.launch(Dispatchers.IO) {
                database.colorCocheDao().deleteAll()
            }
            if (cochesList.size == 0) {
                showToast("No hay resultados para ese color, año o marca")
            }else{
                for (coche in cochesList) {
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
                        Log.d("COLOR-COCHE", colorCoche.toString())


                        GlobalScope.launch(Dispatchers.IO) {
                            database.colorCocheDao().insert(colorCoche)
                        }
                    }
                }
                ColorStorage.setString(hexadecimal.text.toString().trim())
                val intent = Intent(this@Filtrar, ConsultasActivity::class.java)

                startActivity(intent)
            }

        }

        /**
         * Función para comprobar que se han introducido bien los parámetros de búsqueda
         * Nos saca un toast con los errores
         * Finalmente ejecuta la consulta
         */
        fun buscarColor() {
            var colorHex  = ""
            if(hexadecimal.text.trim().startsWith("#")){
                colorHex = hexadecimal.text.toString().trim().substring(1)

            }else{
                colorHex = hexadecimal.text.toString().trim()

            }
            val marcaText = marca.text.toString().trim()
            val yearText = year.text.toString().trim()
            val matchText = match.text.toString().trim()

            val errores = mutableListOf<String>()

            if (!isValidColorHex(colorHex)) {
                errores.add("Formato de color hexadecimal incorrecto")
            }

            if (marcaText.isNotBlank() && !isValidMarca(marcaText)) {
                errores.add("Marca no válida. Marcas válidas: BMW, Ford, Lincoln, Acura, Honda, Mitsubishi, Seat, Volkswagen, Mercury")
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
                    "anio" to yearText,
                    "match" to matchText
                )
                httpClient.executeGetRequest(serverUrl, params)
            }
        }

        /**
         * Función para copiar al portapapeles el color
         * @param text el color a copiar
         * Nos saca un mensaje de aviso de que se ha copiado
         */
        private fun showToast(message: String) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

    // Resto de las funciones de validación sin cambios


        /**
         * Método para comprobar el formato de los colores en hexadecimal.
         * @param colorHex La cadena que se va a validar como un valor hexadecimal de color.
         * @return true si la cadena representa un valor hexadecimal de color válido, false de lo contrario.
         */
        fun isValidColorHex(colorHex: String): Boolean {
            val hexRegex = Regex("^#?([0-9a-fA-F]{6})$")
            println(hexRegex.matches(colorHex))
            return hexRegex.matches(colorHex)
        }

        /**
         * Valida si una marca dada es válida según una lista predefinida de marcas de automóviles.
         * @param marca La marca que se va a validar.
         * @return true si la marca es válida y no está en blanco, false de lo contrario.
         */
        fun isValidMarca(marca: String): Boolean {
            val marcasValidas = setOf("BMW", "Ford", "Lincoln", "Acura", "Honda", "Mitsubishi", "Seat", "Volkswagen", "Mercury")
            return marca.isNotBlank() && marcasValidas.contains(marca)
        }

        /**
         * Valida si un año dado es válido y está en el formato de cuatro dígitos (por ejemplo, "2024").
         *
         * @param year El año que se va a validar.
         * @return true si el año es válido y está en el formato de cuatro dígitos, false de lo contrario.
         */
        fun isValidYear(year: String): Boolean {
            return year.matches(Regex("\\d{4}"))
        }

        /**
         * Valida si una cadena dada representa un valor de coincidencia válido, es decir, un valor numérico
         * que está dentro del rango de 0 a 100, inclusive.
         *
         * @param match La cadena que se va a validar como un valor de coincidencia.
         * @return true si la cadena representa un valor de coincidencia válido, false de lo contrario.
         * @throws flase si se da una excepción
         */
        fun isValidMatch(match: String): Boolean {
            return try {
                val matchValue = match.toDouble()
                matchValue >= 0 && matchValue <= 100
            } catch (e: NumberFormatException) {
                false
            }
        }

        /**
         * Este método se llama cuando se debe crear el menú de opciones en la barra de herramientas.
         *
         * @param menu El menú en el que se inflarán los elementos de la barra de herramientas.
         * @return Devuelve true para mostrar el menú en la barra de herramientas, false de lo contrario.
         */
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
            return true
        }


        /**
         * Función para inflar la barra de navegación.
         * Se le asigna un listener a cada item.
         * Se pone seleccionado el item Filtrar por defecto
         */
        private fun setupBottomMenu() {

            bottomNavigationView = findViewById(R.id.bottom_navigation)
            bottomNavigationView.selectedItemId = R.id.filtrar

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
                    val intent = Intent(this, MainActivity::class.java)

                    startActivity(intent)
                }

            }
            return true
        }
    }