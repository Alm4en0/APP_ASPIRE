package com.tecsup.prototipo_proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Recuperacion1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recuperacion1)
        val btnOlivdarPantalla2= findViewById<Button>(R.id.btnOlivdarPantalla2)
        btnOlivdarPantalla2.setOnClickListener {
            val intent = Intent(this, Recuperacion1Activity::class.java)
            startActivity(intent)
        }
    }
}