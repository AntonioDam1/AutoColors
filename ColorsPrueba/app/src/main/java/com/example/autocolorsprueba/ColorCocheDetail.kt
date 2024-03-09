package com.example.autocolorsprueba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.skydoves.colorpickerview.AlphaTileView

class ColorCocheDetail : AppCompatActivity() {
    private lateinit var botonBorrar : Button
    private lateinit var botonAtras : Button
    private lateinit var toolbar: Toolbar
    private lateinit var alphaTileViewOriginal: AlphaTileView
    private lateinit var alphaTileViewColor: AlphaTileView
    lateinit var bottomNavigationView: BottomNavigationView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_coche_detail)

        botonBorrar = findViewById(R.id.buttonBorrar)
        botonAtras = findViewById(R.id.buttonAtras)
        toolbar = findViewById(R.id.toolbar)
        alphaTileViewColor = findViewById(R.id.alphaTileViewColor)
        alphaTileViewOriginal  =findViewById(R.id.alphaTileViewOriginal)
        setupBottomMenu()
        botonBorrar.setOnClickListener {

        }



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

    private fun onItemSelectedListener(item: MenuItem): Boolean {
        val itemId = item.itemId
        when (item.itemId) {
            R.id.taller -> {
                val intent = Intent(this, ColorMaps::class.java).apply {  }
                val b = Bundle()
//                b.putString("hexadecimal",hexadecimal)
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
}