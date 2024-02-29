package com.example.autocolorsprueba

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts

class galeria : AppCompatActivity() {
    private lateinit var botonImagen: Button
    private lateinit var uri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeria)

        botonImagen = findViewById(R.id.botonImagen)
    }

    fun AbrirGaleria(activity: Activity, requestCode: Int) {
////        // Crear un Intent para abrir la aplicación de la galería
//        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//
//        // Verificar si hay alguna aplicación que pueda manejar la acción de abrir la galería
//        if (intent.resolveActivity(activity.packageManager) != null) {
//            // Iniciar la actividad de la galería esperando un resultado
//            activity.startActivityForResult(intent, requestCode)
//        }
//        val intent = Intent()
//        intent.action = android.content.Intent.ACTION_VIEW
//        intent.type = "image/*"
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        startActivity(intent)

        val intent = Intent(Intent.ACTION_PICK)
        intent.setType("image/*")
        galeriaArl.launch(intent)
    }

    val galeriaArl = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            uri = data?.data
            imagen.setImageURI(uri)
        }
    }

}