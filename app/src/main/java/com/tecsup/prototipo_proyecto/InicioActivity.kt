package com.tecsup.prototipo_proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        val btnInicioSesion = findViewById<Button>(R.id.btnInicioSesion)
        btnInicioSesion.setOnClickListener {
            val intent = Intent(this, SesionActivity::class.java)
            startActivity(intent)
        }

    }
}