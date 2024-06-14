package com.tecsup.prototipo_proyecto.cursos

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tecsup.prototipo_proyecto.EditarPerfilActivity
import com.tecsup.prototipo_proyecto.HomeActivity
import com.tecsup.prototipo_proyecto.R

class CursoActivity : AppCompatActivity(){
    private var currentScreen: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cursos)

        // Configuración del ActionBar
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = "Mis Cursos"

        // Recuperar el valor de currentScreen desde el Intent
        currentScreen = intent.getIntExtra("currentScreen", -1)

        // Configuración del BottomNavigationView
        setupBottomNavigationView()

        // Configuración del RecyclerView de cursos
        setupRecyclerView()
    }

    private fun setupBottomNavigationView() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    startActivity(Intent(this@CursoActivity, HomeActivity::class.java))
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

    private fun setupRecyclerView() {
        val recyclerNotas = findViewById<RecyclerView>(R.id.reciclerCurso)

        val listNotas = listOf(
            Curso1("Certificado1", "Descripción 1", "1520", "Curso2"),
            Curso1("Certificado2", "Descripción 2", "1520", "Curso2"),
            Curso1("Certificado3", "Descripción 3", "1520", "Curso2"),
            Curso1("Certificado4", "Descripción 4", "1520", "Curso2"),
            Curso1("Certificado1", "Descripción 1", "1520", "Curso2"),
            Curso1("Certificado2", "Descripción 2", "1520", "Curso2"),
            Curso1("Certificado3", "Descripción 3", "1520", "Curso2"),
            Curso1("Certificado4", "Descripción 4", "1520", "Curso2")
        )

        val adapter = Curso1Adapter(listNotas)
        recyclerNotas.adapter = adapter
        recyclerNotas.layoutManager = GridLayoutManager(this, 1)
    }

    private fun updateBottomNavigation(bottomNav: BottomNavigationView) {
        bottomNav.menu.getItem(currentScreen ?: 0).isChecked = true
        bottomNav.menu.getItem(currentScreen ?: 0).isEnabled = false
    }
}