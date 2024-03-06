package com.example.autocolorsprueba

import android.R.attr
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.skydoves.colorpickerview.AlphaTileView
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.colorpickerview.ColorPickerView
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import com.skydoves.colorpickerview.sliders.AlphaSlideBar
import com.skydoves.colorpickerview.sliders.BrightnessSlideBar
import java.io.FileNotFoundException


class galeria : AppCompatActivity() {
     val REQUEST_CODE_GALLERY = 200
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
    private lateinit var  ivImage: ImageView

    private var hexadecimal: String = ""
    lateinit var btnImage: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeria)
        colorPickerView = findViewById(R.id.colorPickerViewGalery)
        val bundle = intent.extras
        val color : String = "#FF0000"
        toolbar = findViewById(R.id.toolbar)
        alphaSlider = findViewById(R.id.alphaSlideBar)
        brightnessSlideBar = findViewById(R.id.brightnessSlide)
        alphaTileView = findViewById(R.id.alphaTileView)
        textView = findViewById(R.id.textView)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupBottomMenu()
        registerForContextMenu(alphaTileView)
        colorPickerView.attachAlphaSlider(alphaSlider)
//        colorPickerView.setPaletteDrawable(@drawable)
        colorPickerView.attachBrightnessSlider(brightnessSlideBar)

        btnImage = findViewById(R.id.botonImagen)
        btnImage.setOnClickListener {
            if (Build.VERSION.SDK_INT < 19) {
                var intent = Intent()
                intent.type = "image/*"
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(
                    Intent.createChooser(intent, "Choose Pictures")
                    , REQUEST_CODE_GALLERY
                )
            }
            else { // For latest versions API LEVEL 19+
                var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "image/*"
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }


        }

        colorPickerView.setColorListener(object : ColorEnvelopeListener {
            override fun onColorSelected(envelope: ColorEnvelope, fromUser: Boolean) {
                alphaTileView.setPaintColor(envelope.color)
                textView.text = "#${envelope.hexCode}"
                hexadecimal = "#${envelope.hexCode}"

                toolbar.setBackgroundColor(envelope.color)
                bottomNavigationView.setBackgroundColor(envelope.color)


            }
        })

    }


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

                //   iv_image.setImageURI(imageUri) Here you can assign the picked image uri to your imageview

            }
        }
    }



    private fun onItemSelectedListener(item: MenuItem): Boolean {
        val itemId = item.itemId
        when (item.itemId) {
            R.id.selector -> {
//                val intent = Intent(this, MainActivity::class.java).apply {
                finish()
//                }
//                startActivity(intent)
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
        bottomNavigationView.setSelectedItemId(R.id.selector)

        bottomNavigationView.setOnItemSelectedListener { item -> onItemSelectedListener(item) }

        bottomNavigationView.menu.forEach { menuItem ->
            val badge = bottomNavigationView.getOrCreateBadge(menuItem.itemId)
            badge.isVisible = false
            badge.badgeGravity = BadgeDrawable.TOP_START
//            badgeCounts[menuItem.itemId] = 0
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
            // Crear un Intent para iniciar la actividad de Galería
            val intent = Intent(this@galeria, MainActivity::class.java)
            // Iniciar la actividad
            startActivity(intent)
            // Devolver true para indicar que el evento ha sido consumido
            true
        }

        // Devolver true para mostrar el menú en la barra de acción.


        return true
    }

    //    override fun onContextMenuClosed (menu: Menu) {
//        if (onContextItemSelected(itemCopiar))
//        Toast.makeText( this, "Menú cerrado" , Toast.LENGTH_SHORT).show()
//    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.agregarFavoritos -> {
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
                val intent = Intent(this@galeria, MainActivity::class.java).apply {
                }
                startActivity(intent)
                return true
            }

            else -> return super.onContextItemSelected(item)
        }
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