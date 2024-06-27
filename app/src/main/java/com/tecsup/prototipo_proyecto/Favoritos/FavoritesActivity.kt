package com.tecsup.prototipo_proyecto.Favoritos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tecsup.prototipo_proyecto.HomeActivity
import com.tecsup.prototipo_proyecto.PerfilActivity
import com.tecsup.prototipo_proyecto.R
import com.tecsup.prototipo_proyecto.cursos.CursoActivity
import com.tecsup.prototipo_proyecto.AppDatabase
import com.tecsup.prototipo_proyecto.moduloscurso.ModuloCurso
import com.tecsup.prototipo_proyecto.videoscurso.VideoCursoActivity

class FavoritesActivity : AppCompatActivity() {

    private var currentScreen: Int? = null
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var favoritesAdapter: FavoritesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        // Inicializar ViewModel
        val database = AppDatabase.getDatabase(applicationContext)
        val repository = FavoriteRepository(database.favoriteDao())
        val factory = FavoritesViewModel.Factory(repository)
        viewModel = ViewModelProvider(this, factory)[FavoritesViewModel::class.java]

        // Configurar RecyclerView y Adapter
        val recyclerFavoritos = findViewById<RecyclerView>(R.id.recilerFavoritos)
        favoritesAdapter = FavoritesAdapter(
            onPlayClick = { favorite -> playVideo(favorite) },
            onFavoriteClick = { favorite ->
                viewModel.removeFavorite(favorite)
                Toast.makeText(this@FavoritesActivity, "Eliminado de Favoritos", Toast.LENGTH_SHORT).show()
            }
        )
        recyclerFavoritos.adapter = favoritesAdapter
        recyclerFavoritos.layoutManager = GridLayoutManager(this, 1)

        // Observar cambios en la lista de favoritos
        viewModel.allFavorites.observe(this) { favorites ->
            favoritesAdapter.submitList(favorites)
        }

        // Configuración del Toolbar como ActionBar
        val toolbar: Toolbar = findViewById(R.id.toolbarCursos)
        setSupportActionBar(toolbar)

        // Habilitar la flecha de retroceso
        supportActionBar?.title = "Mis Favoritos"

        // Recuperar el valor de currentScreen desde el Intent
        currentScreen = intent.getIntExtra("currentScreen", -1)

        // Configuración del BottomNavigationView
        setupBottomNavigationView()
    }

    private fun playVideo(favorite: Favorite) {
        val intent = Intent(this, VideoCursoActivity::class.java).apply {
            putExtra("VIDEO_TITLE", favorite.title)
            putExtra("VIDEO_DESCRIPTION", favorite.description)
            putExtra("VIDEO_URL", favorite.videoUrl)
            putExtra("CURRENT_POSITION", 0)
            putExtra("cursoId", favorite.moduleId)
            putExtra("FROM_FAVORITES", true)

            // Crear una lista con un solo elemento para mantener la consistencia
            val singleModuleList = listOf(
                ModuloCurso(
                    id = favorite.moduleId,
                    nombre = favorite.title,
                    descripcion = favorite.description,
                    link = favorite.videoUrl,
                    duracion = null
                )
            )
            putParcelableArrayListExtra("MODULOS_LIST", ArrayList(singleModuleList))
        }
        startActivity(intent)
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