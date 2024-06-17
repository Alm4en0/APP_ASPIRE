package com.tecsup.prototipo_proyecto.cursos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tecsup.prototipo_proyecto.HomeActivity
import com.tecsup.prototipo_proyecto.PerfilActivity
import com.tecsup.prototipo_proyecto.R

class CursoActivity : AppCompatActivity() {

    private var currentScreen: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cursos)

        // Configuración del Toolbar como ActionBar
        val toolbar: Toolbar = findViewById(R.id.toolbarCursos)
        setSupportActionBar(toolbar)

        // Habilitar la flecha de retroceso
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Mis Cursos"

        // Recuperar el valor de currentScreen desde el Intent
        currentScreen = intent.getIntExtra("currentScreen", -1)

        // Configuración del BottomNavigationView
        setupBottomNavigationView()

        // Configuración del RecyclerView de cursos
        setupRecyclerView()
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
                        startActivity(Intent(this@CursoActivity, HomeActivity::class.java))
                        currentScreen = HomeActivity.HOME_SCREEN
                        updateBottomNavigation(bottomNav)
                    }
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
                    if (currentScreen != HomeActivity.FAVORITE_SCREEN) {
                        startActivity(Intent(this, HomeActivity::class.java))
                        intent.putExtra("currentScreen", HomeActivity.PROFILE_SCREEN)
                        currentScreen = HomeActivity.FAVORITE_SCREEN
                        updateBottomNavigation(bottomNav)
                    }
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
        currentScreen?.let { screen ->
            if (screen >= 0 && screen < bottomNav.menu.size()) {
                bottomNav.menu.getItem(screen).isChecked = true
                bottomNav.menu.getItem(screen).isEnabled = false
            }
        }
    }

}
