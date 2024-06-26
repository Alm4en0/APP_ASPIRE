// HomeActivity.kt
package com.tecsup.prototipo_proyecto

import Curso1Adapter
import LoginViewModelFactory
import RetrofitClient
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.tecsup.prototipo_proyecto.cursos.CursoInscripcion
import com.tecsup.tecsupapp.notas.NotaViendo
import com.tecsup.tecsupapp.notas.NotasAdapterViendo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels { LoginViewModelFactory(applicationContext) }
    private lateinit var recyclerNotasHorizontal: RecyclerView
    private lateinit var recyclerCategorias: RecyclerView

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

        // Fetch courses for the user
        fetchUserCourses()
    }

    private fun setupUIComponents() {
        // Initialize RecyclerView for vertical notes
        val recyclerNotas = findViewById<RecyclerView>(R.id.recyclerNotasViendo)
        val listNotas = listOf(
            NotaViendo("Diseño de Interfaces", "Omar Castañeda", "7 Semanas"),
            NotaViendo("Scrum", "Cesar Zavaleta", "7 Semanas"),
            NotaViendo("POO", "Walter Moncada", "7 Semanas"),
            NotaViendo("Humanidades", "Omar Castañeda", "7 Semanas"),
            NotaViendo("Scrum", "Cesar Zavaleta", "7 Semanas"),
            NotaViendo("POO", "Walter Moncada", "7 Semanas")
        )
        val adapter = NotasAdapterViendo(listNotas)
        recyclerNotas.adapter = adapter
        recyclerNotas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Initialize RecyclerView for horizontal notes
        recyclerNotasHorizontal = findViewById(R.id.recyclerNotasViendoHorizontal)
        recyclerNotasHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Initialize RecyclerView for categories
        recyclerCategorias = findViewById(R.id.recyclerCategorias)
        recyclerCategorias.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        fetchUserCourses()

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

    private fun fetchUserCourses() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient(applicationContext).retrofit.getUserCourses()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val cursos = response.body() ?: emptyList()
                        withContext(Dispatchers.Main) {
                            Log.d("HomeActivity", "Cursos recibidos: $cursos")
                            setupRecyclerViews(cursos)
                        }
                    } else {
                        when (response.code()) {
                            401 -> {
                                Log.e(
                                    "HomeActivity",
                                    "Error de autenticación. Redirigiendo a login."
                                )
                                withContext(Dispatchers.Main) {
                                    // Redirect to login screen
                                    // startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
                                    // finish()
                                }
                            }

                            else -> Log.e(
                                "HomeActivity",
                                "Error en la respuesta: ${response.errorBody()?.string()}"
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("HomeActivity", "Excepción: ${e.message}")
            }
        }
    }

    private fun setupRecyclerViews(cursos: List<CursoInscripcion>) {
        if (cursos.isNotEmpty()) {
            // Setup RecyclerView for horizontal notes
            val adapterHorizontal = Curso1Adapter(cursos) { curso ->
                // Handle item click
                Log.d("HomeActivity", "Curso clickeado: $curso") // Debugging
            }
            recyclerNotasHorizontal.adapter = adapterHorizontal

            // Extract categories from courses
            val categorias = cursos.map { Categoria2(it.categoria_nombre) }.distinctBy { it.nombre }
            val adapterCategorias = Categoria2Adapter(categorias)
            recyclerCategorias.adapter = adapterCategorias
        } else {
            Log.d("HomeActivity", "No se encontraron cursos para mostrar.") // Debugging
        }
    }
}
