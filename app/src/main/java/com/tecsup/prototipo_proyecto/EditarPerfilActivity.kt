package com.tecsup.prototipo_proyecto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tecsup.prototipo_proyecto.cursos.CursoActivity

class EditarPerfilActivity : AppCompatActivity() {
    private var currentScreen: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)

        // Recuperar el valor de currentScreen desde el Intent
        currentScreen = intent.getIntExtra("currentScreen", -1)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    startActivity(Intent(this@EditarPerfilActivity, HomeActivity::class.java))
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
                    val intent = Intent(this, EditarPerfilActivity::class.java)
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
