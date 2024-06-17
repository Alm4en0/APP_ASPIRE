package com.tecsup.prototipo_proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tecsup.prototipo_proyecto.R.id.toolbarCursos
import com.tecsup.prototipo_proyecto.auth.LoginActivity
import com.tecsup.prototipo_proyecto.cursos.CursoActivity

class PerfilActivity : AppCompatActivity() {

    private var currentScreen: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // Configuración del Toolbar como ActionBar
        val toolbar: Toolbar = findViewById(toolbarCursos)
        setSupportActionBar(toolbar)

        // Habilitar la flecha de retroceso
        supportActionBar?.title = "Mi Perfil"

        val btnEditarPerfil = findViewById<Button>(R.id.btnEditarPerfil)
        btnEditarPerfil.setOnClickListener {
            val intent = Intent(this, EditarPerfilActivity::class.java)
            intent.putExtra("currentScreen", currentScreen)
            startActivity(intent)
        }

        // Recuperar el valor de currentScreen desde el Intent
        currentScreen = intent.getIntExtra("currentScreen", HomeActivity.PROFILE_SCREEN)

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
                    true
                }
                else -> false
            }
        }
        updateBottomNavigation(bottomNav)
    }

    override fun onResume() {
        super.onResume()
        currentScreen = intent.getIntExtra("currentScreen", HomeActivity.PROFILE_SCREEN)
        updateBottomNavigation(findViewById(R.id.bottom_navigation))
    }

    private fun updateBottomNavigation(bottomNav: BottomNavigationView) {
        bottomNav.menu.getItem(currentScreen ?: 0).isChecked = true
        bottomNav.menu.getItem(currentScreen ?: 0).isEnabled = false
    }
}