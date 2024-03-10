package com.example.autocolorsprueba

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ColorMaps : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMyLocationClickListener {

    private lateinit var map:GoogleMap

    companion object{
        const val REQUEST_CODE_LOCATION = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_maps)
        createFragment()
    }

    private fun createFragment() {
        val mapFragment : SupportMapFragment = supportFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
//        createMarker()
        map.setOnMyLocationClickListener(this)
        enableLocation()
    }

    private fun createMarker() {
        val coordinate = LatLng(42.80847968283196, -1.637966036421036)
        val market = MarkerOptions().position(coordinate).title("Taller Mecánico Jehova Es Mi Luz")
        map.addMarker(market)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinate, 18f),1000,null
        )
    }

    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(this,
        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun enableLocation(){
        if (!::map.isInitialized) return
        if(isLocationPermissionGranted()){
            map.isMyLocationEnabled = true
        }else{
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(this, "Debes otorgar permisos a la aplicación: Ajustes->Permisos->Aceptar",
                Toast.LENGTH_SHORT).show()
        }else{
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
        if (!isLocationPermissionGranted()){
            map.isMyLocationEnabled = true
            Toast.makeText(
                this,
                "Para activar la localización debes otorgar permisos a la aplicación: Ajustes->Permisos->Aceptar",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(this,"Tu ubicación es ${p0.latitude}, ${p0.longitude}", Toast.LENGTH_SHORT).show()
    }
}