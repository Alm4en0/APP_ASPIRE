package com.tecsup.prototipo_proyecto


import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class EditarPerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar_perfil)
        val intent = Intent(this, PerfilActivity::class.java)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    // Acción para el elemento Home
                    startActivity(Intent(this, PerfilActivity::class.java))
                    true
                }
                R.id.circleplay -> {
                    // Acción para el elemento Circle Play
                    true
                }
                R.id.heart -> {
                    true
                }
                R.id.gato -> {
                    // Acción para el elemento Gato

                    true
                }
                else -> false
            }
        }
    }
}
