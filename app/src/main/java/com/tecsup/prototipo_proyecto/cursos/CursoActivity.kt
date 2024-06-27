package com.tecsup.prototipo_proyecto.cursos

import Curso2Adapter
import RetrofitClient
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tecsup.prototipo_proyecto.Favoritos.FavoritesActivity
import com.tecsup.prototipo_proyecto.HomeActivity
import com.tecsup.prototipo_proyecto.PerfilActivity
import com.tecsup.prototipo_proyecto.R
import com.tecsup.prototipo_proyecto.moduloscurso.ModuloCursoActivity
import com.tecsup.prototipo_proyecto.network.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CursoActivity : AppCompatActivity() {

    private var currentScreen: Int? = null
    private lateinit var recyclerCursos: RecyclerView
    private lateinit var cursoAdapter: Curso2Adapter
    private lateinit var apiService: ApiService

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

        // Inicializa Retrofit
        apiService = RetrofitClient(this).retrofit

        // Realiza la llamada a la API para obtener los cursos del usuario
        loadUserCourses()
    }

    override fun onResume() {
        super.onResume()
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.circleplay
        currentScreen = HomeActivity.COURSE_SCREEN
        updateBottomNavigation(bottomNav)
    }

    private fun setupRecyclerView() {
        recyclerCursos = findViewById(R.id.reciclerCurso)
        recyclerCursos.layoutManager = LinearLayoutManager(this)
        cursoAdapter = Curso2Adapter(emptyList(), { cursoId ->
            // Verificar que el curso tenga un id válido
            Log.d("CursoActivity", "Curso seleccionado: $cursoId")
            // Al hacer clic en un curso, abrir la actividad de módulos del curso
            val intent = Intent(this, ModuloCursoActivity::class.java)
            intent.putExtra("cursoId", cursoId) // Aquí pasas el cursoId directamente
            startActivity(intent)
        })
        recyclerCursos.adapter = cursoAdapter
    }

    private fun loadUserCourses() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getUserCourses()
                if (response.isSuccessful) {
                    response.body()?.let { cursos ->
                        withContext(Dispatchers.Main) {
                            // Actualiza el adaptador con la lista de cursos obtenida
                            cursoAdapter.updateCursos(cursos)
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@CursoActivity, "Error al obtener los cursos", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@CursoActivity, "Fallo en la llamada: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        // Navegar hacia la pantalla de inicio al presionar la flecha de retroceso
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }

    private fun setupBottomNavigationView() {
        // Configuración del BottomNavigationView y gestión de selecciones de menú
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    // Ir a la pantalla de inicio
                    if (currentScreen != HomeActivity.HOME_SCREEN) {
                        startActivity(Intent(this@CursoActivity, HomeActivity::class.java))
                        currentScreen = HomeActivity.HOME_SCREEN
                        updateBottomNavigation(bottomNav)
                    }
                    true
                }
                R.id.circleplay -> {
                    // Ir a la pantalla de cursos
                    val intent = Intent(this, CursoActivity::class.java)
                    intent.putExtra("currentScreen", HomeActivity.COURSE_SCREEN)
                    startActivity(intent)
                    true
                }
                R.id.heart -> {
                    // Ir a la pantalla de favoritos
                    val intent = Intent(this, FavoritesActivity::class.java)
                    intent.putExtra("currentScreen", HomeActivity.FAVORITE_SCREEN)
                    startActivity(intent)
                    true
                }
                R.id.gato -> {
                    // Ir a la pantalla de perfil
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
        // Actualizar el estado del BottomNavigationView basado en currentScreen
        currentScreen?.let { screen ->
            if (screen >= 0 && screen < bottomNav.menu.size()) {
                bottomNav.menu.getItem(screen).isChecked = true
                bottomNav.menu.getItem(screen).isEnabled = false
            }
        }
    }
}
