package com.tecsup.prototipo_proyecto

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class EditarPerfilActivity : AppCompatActivity() {
    private var currentScreen: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar_perfil)

        // Recuperar el valor de currentScreen desde el Intent
        currentScreen = intent.getIntExtra("currentScreen", -1)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    startActivity(Intent(this@EditarPerfilActivity, PerfilActivity::class.java))
                    currentScreen = PerfilActivity.HOME_SCREEN
                    updateBottomNavigation(bottomNav)
                    true
                }
                // ...
                else -> false
            }
        }
        updateBottomNavigation(bottomNav)
    }

    private fun updateBottomNavigation(bottomNav: BottomNavigationView) {
        bottomNav.menu.getItem(currentScreen ?: 0).isChecked = true
        bottomNav.menu.getItem(currentScreen ?: 0).isEnabled = false
    }
}