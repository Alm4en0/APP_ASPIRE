package com.tecsup.prototipo_proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tecsup.prototipo_proyecto.auth.LoginActivity
import com.tecsup.prototipo_proyecto.cursos.CursoActivity

class PerfilActivity : AppCompatActivity() {

    private var currentScreen: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // Configuración del ActionBar
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = "Mi Perfil"

        val btnEditarPerfil = findViewById<Button>(R.id.btnEditarPerfil)
        btnEditarPerfil.setOnClickListener {
            val intent = Intent(this, EditarPerfilActivity::class.java)
            startActivity(intent)
        }

        // Recuperar el valor de currentScreen desde el Intent
        currentScreen = intent.getIntExtra("currentScreen", -1)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    startActivity(Intent(this@PerfilActivity, HomeActivity::class.java))
                    currentScreen = HomeActivity.HOME_SCREEN
                    updateBottomNavigation(bottomNav)
                    true
                }
                R.id.circleplay -> {
                    val intent = Intent(this, CursoActivity::class.java)
                    intent.putExtra("currentScreen", HomeActivity.COURSE_SCREEN)
                    startActivity(intent)
                    currentScreen = HomeActivity.COURSE_SCREEN
                    updateBottomNavigation(bottomNav)
                    true
                }
                R.id.heart -> {
                    currentScreen = HomeActivity.FAVORITE_SCREEN
                    updateBottomNavigation(bottomNav)
                    true
                }
                R.id.gato -> {
                    val intent = Intent(this, PerfilActivity::class.java)
                    intent.putExtra("currentScreen", HomeActivity.PROFILE_SCREEN)
                    startActivity(intent)
                    currentScreen = HomeActivity.PROFILE_SCREEN
                    updateBottomNavigation(bottomNav)
                    true
                }
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