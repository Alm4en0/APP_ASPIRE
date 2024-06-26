// HomeActivity.kt
package com.tecsup.prototipo_proyecto

import LoginViewModelFactory
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tecsup.prototipo_proyecto.Favoritos.FavoritesActivity
import com.tecsup.prototipo_proyecto.PerfilActivity
import com.tecsup.prototipo_proyecto.auth.LoginViewModel
import com.tecsup.prototipo_proyecto.categorias2.Categoria2
import com.tecsup.prototipo_proyecto.categorias2.Categoria2Adapter
import com.tecsup.prototipo_proyecto.cursos.CursoActivity
import com.tecsup.prototipo_proyecto.notasViendoHorizontal.NotaHorizontal
import com.tecsup.prototipo_proyecto.notasViendoHorizontal.NotasAdapterHorizontal
import com.tecsup.tecsupapp.notas.NotaViendo
import com.tecsup.tecsupapp.notas.NotasAdapterViendo

class HomeActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels { LoginViewModelFactory(applicationContext) }
    private var currentScreen: Int = HOME_SCREEN

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

        // Setup UI components
        setupUIComponents()

        // Observe changes in username from ViewModel
        val userNameTextView = findViewById<TextView>(R.id.usernameTXT)
        viewModel.userName.observe(this, Observer { username ->
            userNameTextView.text = username
        })
    }

    private fun setupUIComponents() {
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
            NotaHorizontal("Diseño de Interfaces", "https://img.freepik.com/fotos-premium/casa-mano-humana-fondo_488220-5956.jpg?w=996"),
            NotaHorizontal("Diseño de Interfaces", "https://img.freepik.com/fotos-premium/casa-mano-humana-fondo_488220-5956.jpg?w=996"),
            NotaHorizontal("Diseño de Interfaces", "https://img.freepik.com/fotos-premium/casa-mano-humana-fondo_488220-5956.jpg?w=996"),
            NotaHorizontal("Diseño de Interfaces", "https://img.freepik.com/fotos-premium/casa-mano-humana-fondo_488220-5956.jpg?w=996"),
            NotaHorizontal("Diseño de Interfaces", "https://img.freepik.com/fotos-premium/casa-mano-humana-fondo_488220-5956.jpg?w=996")
        )
        val adapter2 = NotasAdapterHorizontal(listNotas2)
        recyclerNotasHorizontal.adapter = adapter2
        recyclerNotasHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Recycler Categorias
        val recyclerCategorias = findViewById<RecyclerView>(R.id.recyclerCategorias)
        val listCategorias = listOf(
            Categoria2("Finanzas"),
            Categoria2("Desarrollo Personal"),
            Categoria2("Bienes Raíces"),
            Categoria2("Finanzas"),
            Categoria2("Finanzas"),
            Categoria2("Finanzas")
        )

        val adapter2Categoria = Categoria2Adapter(listCategorias)
        recyclerCategorias.adapter = adapter2Categoria
        recyclerCategorias.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Setup BottomNavigationView
        setupBottomNavigationView()

        // "Ver Todo" button action
        val txtVerTodo = findViewById<TextView>(R.id.txtVerTodo)
        txtVerTodo.setOnClickListener {
            val intent = Intent(this, CursoActivity::class.java)
            intent.putExtra("currentScreen", currentScreen)
            startActivity(intent)
        }
    }

    private fun setupBottomNavigationView() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    if (currentScreen != HOME_SCREEN) {
                        currentScreen = HOME_SCREEN
                        updateBottomNavigation(bottomNav)
                    }
                    true
                }
                R.id.circleplay -> {
                    val intent = Intent(this, CursoActivity::class.java)
                    intent.putExtra("currentScreen", COURSE_SCREEN)
                    startActivity(intent)
                    true
                }
                R.id.heart -> {
                    val intent = Intent(this, FavoritesActivity::class.java)
                    intent.putExtra("currentScreen", FAVORITE_SCREEN)
                    startActivity(intent)
                    true
                }
                R.id.gato -> {
                    val intent = Intent(this, PerfilActivity::class.java)
                    intent.putExtra("currentScreen", PROFILE_SCREEN)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        updateBottomNavigation(bottomNav)
    }

    private fun updateBottomNavigation(bottomNav: BottomNavigationView) {
        currentScreen.let { screen ->
            if (screen in 0 until bottomNav.menu.size()) {
                bottomNav.menu.getItem(screen).isChecked = true
                bottomNav.menu.getItem(screen).isEnabled = false
            }
        }
    }
}
