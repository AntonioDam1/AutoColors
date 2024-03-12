package com.example.autocolorsprueba


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView


/**
 * Actividad que muestra un mapa de Google Maps con funcionalidades relacionadas con la ubicación.
 * Implementa las interfaces necesarias para interactuar con el mapa y gestionar eventos de clic en la ubicación del usuario.
 */
class ColorMaps : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMyLocationClickListener {


    private lateinit var map: GoogleMap
    private lateinit var toolbar: Toolbar
    lateinit var bottomNavigationView: BottomNavigationView






    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_maps)
        createFragment()
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupBottomMenu()


    }


    /**
     * Función para inflar la barra de navegación.
     * Se le asigna un listener a cada item.
     * Se pone seleccionado el item Taller por defecto
     */
    private fun setupBottomMenu() {
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.taller
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
            R.id.filtrar -> {
                val intent = Intent(this, Filtrar::class.java).apply {  }
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

    /**
     * Crea el fragmento del mapa y lo muestra.
     */
    private fun createFragment() {
        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    /**
     * Método llamado cuando el mapa de Google está listo para ser utilizado.
     * Se encarga de configurar el mapa, habilitar la detección de clics en la ubicación del usuario
     * y agregar marcadores al mapa.
     *
     * @param googleMap El objeto GoogleMap que representa el mapa que se ha cargado y está listo para ser utilizado.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setOnMyLocationClickListener(this)
        enableLocation()
        createMarkers()
    }


    /**
     * Función con la lista de talleres
     * Crea los marcadores
     */
    private fun createMarkers() {
        val talleres = listOf(
            Taller("TALLERES MENDEBALDEA", 42.81068480607709, -1.673602270612605),
            Taller("Talleres Mendikur", 42.82133925028498, -1.6916772616986957),
            Taller("RG", 42.821993844713575, -1.691965294631434),
            Taller("Satori", 42.821890904537305, -1.6914294246819421),
            Taller("SAGAMOVIL", 42.81994436712439, -1.6899238853000362),
            Taller("I10Motor", 42.82075855128641, -1.6951422379034222),
            Taller("Mecanizados", 42.81885671061577, -1.6944359241878335),
            Taller("AutoNariño", 42.81283239900596, -1.687110050653027),
            Taller("Talleres Narvik", 42.812966061802506, -1.6841675942612542),
            Taller("RodiMotors", 42.81158470986501, -1.641844510973203),
            Taller("Talleres Baztan", 42.8116791569115, -1.6381108759824803),
            Taller("Talleres LaTaza", 42.807617803675555, -1.6380250452930383),
            Taller("Talleres Labritaller", 42.81731423637317, -1.6399133204641385),
            Taller("Talleres Santa Marta", 42.81079764559236, -1.6334760187559962),
            Taller("Taller Ayra", 42.82566815112215, -1.646466168302675),
            Taller("Talleres Joan", 42.823759319310575, -1.6507828760525982)
        )


        for (taller in talleres) {
            val coordinate = LatLng(taller.latitude, taller.longitude)
            val marker = MarkerOptions().position(coordinate).title(taller.name)
            map.addMarker(marker)
        }
    }


    /**
     * Clase de datos que representa un taller.
     *
     * @param name      El nombre del taller.
     * @param latitude  La latitud del taller.
     * @param longitude La longitud del taller.
     */
    data class Taller(val name: String, val latitude: Double, val longitude: Double)


    /**
     * Verifica si se ha concedido el permiso de ubicación.
     */
    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(this,
        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED


    /**
     * Habilita la funcionalidad de ubicación en el mapa.
     * Verifica si el mapa está inicializado y si se han otorgado permisos de ubicación.
     * Si los permisos están otorgados, activa la ubicación del usuario en el mapa y centra la cámara en su posición.
     * Luego, agrega los marcadores de los talleres al mapa.
     * Si los permisos no están otorgados, solicita al usuario que los conceda.
     */
    private fun enableLocation() {
        if (!::map.isInitialized) return
        if (isLocationPermissionGranted()) {
            map.isMyLocationEnabled = true


            // Mueve la cámara a la ubicación del usuario una vez que esté habilitada
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        val userLocation = LatLng(location.latitude, location.longitude)
                        map.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(userLocation, 15f),
                            1000,
                            null
                        )
                    }
                }


            // Agrega el marcador del taller después de centrar en la ubicación del usuario
            createMarkers()
        } else {
            requestLocationPermission()
        }
    }


    /**
     * Solicita al usuario que otorgue permisos de ubicación a la aplicación.
     * Muestra un mensaje de solicitud de permisos si el usuario aún no ha otorgado permisos.
     * Si el usuario ya ha denegado previamente los permisos, se muestra un mensaje explicativo.
     * Si el usuario ha marcado la opción "No volver a preguntar", se lanza directamente la solicitud de permisos.
     */
    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, "Debes otorgar permisos a la aplicación: Ajustes->Permisos->Aceptar",
                Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE_LOCATION)
        }
    }


    /**
     * Callback que se invoca cuando el usuario responde a la solicitud de permisos de la aplicación.
     * Se verifica si los permisos de ubicación fueron otorgados.
     * Si se otorgaron los permisos, se activa la ubicación del usuario en el mapa.
     * Si los permisos no fueron otorgados, se muestra un mensaje explicativo al usuario.
     *
     * @param requestCode  Código de solicitud de permisos.
     * @param permissions  Arreglo de permisos solicitados.
     * @param grantResults Arreglo de resultados de la solicitud de permisos.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    map.isMyLocationEnabled = true
                } else {
                    Toast.makeText(
                        this,
                        "Para activar la localización debes otorgar permisos a la aplicación: Ajustes->Permisos->Aceptar",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }


    /**
     * Método que se llama cuando se reanudan los fragmentos de la actividad.
     * Verifica si el mapa está inicializado y si se han otorgado permisos de ubicación.
     * Si los permisos no están otorgados, se activa la ubicación del usuario en el mapa y se muestra un mensaje informativo.
     */
    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::map.isInitialized) return
        if (!isLocationPermissionGranted()) {
            map.isMyLocationEnabled = true
            Toast.makeText(
                this,
                "Para activar la localización debes otorgar permisos a la aplicación: Ajustes->Permisos->Aceptar",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    /**
     * Método que se invoca cuando el usuario hace clic en su ubicación en el mapa.
     * Muestra un mensaje emergente que indica la latitud y longitud de la ubicación del usuario.
     *
     * @param p0 La ubicación del usuario en el mapa.
     */
    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(this, "Tu ubicación es ${p0.latitude}, ${p0.longitude}", Toast.LENGTH_SHORT).show()
    }
}
