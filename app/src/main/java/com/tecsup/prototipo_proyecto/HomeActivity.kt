package com.tecsup.prototipo_proyecto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tecsup.prototipo_proyecto.categorias.Categoria
import com.tecsup.prototipo_proyecto.categorias.CategoriaAdapter
import com.tecsup.prototipo_proyecto.curso.CursoActivity
import com.tecsup.tecsupapp.notas.NotaViendo
import com.tecsup.tecsupapp.notas.NotasAdapterViendo
import com.tecsup.prototipo_proyecto.notasViendoHorizontal.NotaHorizontal
import com.tecsup.prototipo_proyecto.notasViendoHorizontal.NotasAdapterHorizontal

class HomeActivity : AppCompatActivity() {

    private var currentScreen: Int? = null

    companion object {
        const val HOME_SCREEN = 0
        const val COURSE_SCREEN = 1
        const val FAVORITE_SCREEN = 2
        const val PROFILE_SCREEN = 3
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

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
        recyclerNotas.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Content 2
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
        recyclerNotasHorizontal.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        //Recycler Categorias
        val recyclerCategorias = findViewById<RecyclerView>(R.id.recyclerCategorias)
        val listCategorias = listOf(
            Categoria("Finanzas"),
            Categoria("Desarrollo Personal"),
            Categoria("Bienes Raizes"),
            Categoria("Finanzas"),
            Categoria("Finanzas"),
            Categoria("Finanzas"),
        )

        val adapterCategoria = CategoriaAdapter(listCategorias)
        recyclerCategorias.adapter = adapterCategoria
        recyclerCategorias.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        currentScreen = HOME_SCREEN

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    currentScreen = HOME_SCREEN
                    updateBottomNavigation(bottomNav)
                    true
                }

                R.id.circleplay -> {
                    val intent = Intent(this, CursoActivity::class.java)
                    intent.putExtra("currentScreen", COURSE_SCREEN)
                    startActivity(intent)
                    currentScreen = COURSE_SCREEN
                    updateBottomNavigation(bottomNav)
                    true
                }

                R.id.heart -> {
                    currentScreen = FAVORITE_SCREEN
                    updateBottomNavigation(bottomNav)
                    true
                }

                R.id.gato -> {
                    val intent = Intent(this, EditarPerfilActivity::class.java)
                    intent.putExtra("currentScreen", PROFILE_SCREEN)
                    startActivity(intent)
                    currentScreen = PROFILE_SCREEN
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
