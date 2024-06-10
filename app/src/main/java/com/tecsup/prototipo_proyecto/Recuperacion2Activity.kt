package com.tecsup.prototipo_proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Recuperacion2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recuperacion2)
        val btnOlivdarPantalla3= findViewById<Button>(R.id.btnOlivdarPantalla3)
        btnOlivdarPantalla3.setOnClickListener {
            val intent = Intent(this,SesionActivity::class.java)
            startActivity(intent)
        }
    }
}