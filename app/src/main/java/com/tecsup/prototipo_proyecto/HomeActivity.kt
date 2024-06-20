package com.tecsup.prototipo_proyecto

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tecsup.prototipo_proyecto.Favoritos.FavoritesActivity
import com.tecsup.prototipo_proyecto.auth.LoginRepository
import com.tecsup.prototipo_proyecto.cursos.CursoActivity
import com.tecsup.prototipo_proyecto.notasViendoHorizontal.NotaHorizontal
import com.tecsup.prototipo_proyecto.notasViendoHorizontal.NotasAdapterHorizontal
import com.tecsup.tecsupapp.notas.NotaViendo
import com.tecsup.tecsupapp.notas.NotasAdapterViendo
import com.tecsup.prototipo_proyecto.PerfilActivity

class HomeActivity : AppCompatActivity() {

    private var currentScreen: Int = HOME_SCREEN
    private lateinit var userName: String

    companion object {
        const val HOME_SCREEN = 0
        const val COURSE_SCREEN = 1
        const val FAVORITE_SCREEN = 2
        const val PROFILE_SCREEN = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Hide the ActionBar
        supportActionBar?.hide()

        // Retrieve the username from SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        userName = sharedPreferences.getString("username", "Usuario") ?: "Usuario"

        // Set the username in the TextView
        val userNameTextView = findViewById<TextView>(R.id.usernameTXT)
        userNameTextView.text = userName

        // Initialize RecyclerView for vertical notes
        val recyclerNotas = findViewById<RecyclerView>(R.id.recyclerNotasViendo)
        val listNotas = listOf(
            NotaViendo("Diseño de Interfaces", "Omar Castañeda", "7 Semanas"),
            NotaViendo("Scrum", "Cesar Zavaleta", "7 Semanas"),
            NotaViendo("POO", "Walter Moncada", "7 Semanas"),
            NotaViendo("Humanidades", "Omar Castañeda", "7 Semanas"),
            NotaViendo("Scram", "Cesar Zavaleta", "7 Semanas"),
            NotaViendo("POO", "Walter Moncada", "7 Semanas")
        )
        val adapter = NotasAdapterViendo(listNotas)
        recyclerNotas.adapter = adapter
        recyclerNotas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Initialize RecyclerView for horizontal notes
        val recyclerNotasHorizontal = findViewById<RecyclerView>(R.id.recyclerNotasViendoHorizontal)
        val listNotas2 = listOf(
            NotaHorizontal(
                "Diseño de Interfaces",
                "https://img.freepik.com/fotos-premium/casa-mano-humana-fondo_488220-5956.jpg?w=996"
            ),
            NotaHorizontal(
                "Diseño de Interfaces",
                "https://img.freepik.com/fotos-premium/casa-mano-humana-fondo_488220-5956.jpg?w=996"
            ),
            NotaHorizontal(
                "Diseño de Interfaces",
                "https://img.freepik.com/fotos-premium/casa-mano-humana-fondo_488220-5956.jpg?w=996"
            ),
            NotaHorizontal(
                "Diseño de Interfaces",
                "https://img.freepik.com/fotos-premium/casa-mano-humana-fondo_488220-5956.jpg?w=996"
            ),
            NotaHorizontal(
                "Diseño de Interfaces",
                "https://img.freepik.com/fotos-premium/casa-mano-humana-fondo_488220-5956.jpg?w=996"
            ),
            NotaHorizontal(
                "Diseño de Interfaces",
                "https://img.freepik.com/fotos-premium/casa-mano-humana-fondo_488220-5956.jpg?w=996"
            )
        )
        val adapter2 = NotasAdapterHorizontal(listNotas2)
        recyclerNotasHorizontal.adapter = adapter2
        recyclerNotasHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Initialize BottomNavigationView
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    if (currentScreen != HOME_SCREEN) {
                        currentScreen = HOME_SCREEN
                        // Update UI or perform any necessary actions for Home screen
                    }
                    true
                }
                R.id.circleplay -> {
                    startActivity(Intent(this, CursoActivity::class.java))
                    true
                }
                R.id.heart -> {
                    startActivity(Intent(this, FavoritesActivity::class.java))
                    true
                }
                R.id.gato -> {
                    startActivity(Intent(this, PerfilActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // Set initial state
        updateBottomNavigation(bottomNav)
    }

    private fun updateBottomNavigation(bottomNav: BottomNavigationView) {
        bottomNav.menu.getItem(currentScreen).isChecked = true
        bottomNav.menu.getItem(currentScreen).isEnabled = false
    }
}
