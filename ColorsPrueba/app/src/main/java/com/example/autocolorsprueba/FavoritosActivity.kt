package com.example.autocolorsprueba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.view.View.OnCreateContextMenuListener
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autocolorsprueba.adapter.ColorCocheAdapter
import com.example.autocolorsprueba.database.CochesRoomDatabase
import com.example.autocolorsprueba.model.entity.ColorCoche
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoritosActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var itemBorra :MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupBottomMenu()

        initRecyclerView()
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
//    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
//        super.onCreateContextMenu(menu, v, menuInfo)
//        menuInflater.inflate(R.menu.delete_item, menu)
//        itemBorra = menu.findItem(R.id.borrar)
//    }

//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.borrar -> {
//                var database  = CochesRoomDatabase.getInstance(this)
//                database.colorCocheDao().delete(itemBorra.isVisible)
//
//                // Lógica para la opción 1
//                true
//            }
//            R.id.opcion_menu_2 -> {
//                // Lógica para la opción 2
//                true
//            }
//            else -> super.onContextItemSelected(item)
//        }
//    }
    private fun onItemSelectedListener(item: MenuItem): Boolean {
        val itemId = item.itemId
        when (item.itemId) {
            R.id.galeria -> {
                val intent = Intent(this, galeria::class.java).apply {  }
                val b = Bundle()

                startActivity(intent)
            }
            R.id.selector -> {
                val intent = Intent(this, MainActivity::class.java).apply {  }

                startActivity(intent)
            }

//            R.id. page_fav -> {
//                showPageFragment(R.drawable. ic_fav, R.string. bottom_nav_fav)
//                return true
//            }
        }
        return true
    }

    fun initRecyclerView(){
        var database  = CochesRoomDatabase.getInstance(this)
        var cochesColores : List<ColorCoche>
        GlobalScope.launch(Dispatchers.IO) {
            cochesColores= database.colorCocheDao().getAll()
            runOnUiThread {
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerFavs)
                recyclerView.layoutManager = LinearLayoutManager(this@FavoritosActivity)
                recyclerView.adapter = ColorCocheAdapter(cochesColores)
            }
        }

    }

}