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


    private fun setupBottomMenu() {


        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.taller


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
            R.id.filtrar -> {
                val intent = Intent(this, Filtrar::class.java).apply {  }
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




    private fun createFragment() {
        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setOnMyLocationClickListener(this)
        enableLocation()
        createMarkers()
    }


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


    data class Taller(val name: String, val latitude: Double, val longitude: Double)


    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(this,
        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED


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


    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, "Debes otorgar permisos a la aplicación: Ajustes->Permisos->Aceptar",
                Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE_LOCATION)
        }
    }


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


    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(this, "Tu ubicación es ${p0.latitude}, ${p0.longitude}", Toast.LENGTH_SHORT).show()
    }
}
