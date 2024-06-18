package com.tecsup.prototipo_proyecto

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tecsup.prototipo_proyecto.cursos.CursoActivity

class FavoritesActivity : AppCompatActivity() {

    private var currentScreen: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        // Configuración del Toolbar como ActionBar
        val toolbar: Toolbar = findViewById(R.id.toolbarCursos)
        setSupportActionBar(toolbar)

        // Habilitar la flecha de retroceso
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Mis Favoritos"

        // Recuperar el valor de currentScreen desde el Intent
        currentScreen = intent.getIntExtra("currentScreen", -1)

        // Configuración del BottomNavigationView
        setupBottomNavigationView()
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }

    private fun setupBottomNavigationView() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    if (currentScreen != HomeActivity.HOME_SCREEN) {
                        startActivity(Intent(this@FavoritesActivity, HomeActivity::class.java))
                        currentScreen = HomeActivity.HOME_SCREEN
                        updateBottomNavigation(bottomNav)
                    }
                    true
                }
                R.id.circleplay -> {
                    val intent = Intent(this, CursoActivity::class.java)
                    intent.putExtra("currentScreen", HomeActivity.COURSE_SCREEN)
                    startActivity(intent)
                    true
                }
                R.id.heart -> {
                    val intent = Intent(this, FavoritesActivity::class.java)
                    intent.putExtra("currentScreen", HomeActivity.FAVORITE_SCREEN)
                    startActivity(intent)
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

    private fun updateBottomNavigation(bottomNav: BottomNavigationView) {
        currentScreen?.let { screen ->
            if (screen >= 0 && screen < bottomNav.menu.size()) {
                bottomNav.menu.getItem(screen).isChecked = true
                bottomNav.menu.getItem(screen).isEnabled = false
            }
        }
    }
}