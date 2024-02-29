package com.example.autocolorsprueba

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView.ItemView
import com.google.android.material.appbar.AppBarLayout
import com.skydoves.colorpickerview.AlphaTileView
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.colorpickerview.ColorPickerView
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import com.skydoves.colorpickerview.sliders.AlphaSlideBar
import com.skydoves.colorpickerview.sliders.BrightnessSlideBar
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

class MainActivity : AppCompatActivity() {

    private lateinit var colorPickerView: ColorPickerView
    private lateinit var alphaSlider: AlphaSlideBar
    private lateinit var brightnessSlideBar: BrightnessSlideBar
    private lateinit var alphaTileView: AlphaTileView
    private lateinit var textView: TextView
    private lateinit var appToolbar: AppBarLayout
    private lateinit var itemCopiar: MenuItem
    private lateinit var itemAgregar: MenuItem

    private var hexadecimal: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorPickerView = findViewById(R.id.colorPickerView)
        alphaSlider = findViewById(R.id.alphaSlideBar)
        brightnessSlideBar = findViewById(R.id.brightnessSlide)
        alphaTileView = findViewById(R.id.alphaTileView)
        textView = findViewById(R.id.textView)
        appToolbar = findViewById(R.id.appBarLayout)



        registerForContextMenu(alphaTileView)

        colorPickerView.setColorListener(object : ColorEnvelopeListener {
            override fun onColorSelected(envelope: ColorEnvelope, fromUser: Boolean) {
                alphaTileView.setPaintColor(envelope.color)
                textView.text = "#${envelope.hexCode}"
                hexadecimal = "#${envelope.hexCode}"

                appToolbar.setBackgroundColor(envelope.color)


            }
        })

        colorPickerView.attachAlphaSlider(alphaSlider)
        colorPickerView.attachBrightnessSlider(brightnessSlideBar)
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.fav_menu, menu)
        itemCopiar = menu.findItem(R.id.agregarFavoritos)
        itemAgregar = menu.findItem(R.id.itemcopiar)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.fav_menu, menu)
        itemCopiar = menu.findItem(R.id.agregarFavoritos)
        itemAgregar = menu.findItem(R.id.itemcopiar)
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
