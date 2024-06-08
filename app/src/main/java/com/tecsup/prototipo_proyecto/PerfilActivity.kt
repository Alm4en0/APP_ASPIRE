package com.tecsup.prototipo_proyecto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tecsup.prototipo_proyecto.R.layout.activity_perfil
import com.tecsup.prototipo_proyecto.categorias.Categoria
import com.tecsup.prototipo_proyecto.categorias.CategoriaAdapter
import com.tecsup.tecsupapp.notas.NotaViendo
import com.tecsup.tecsupapp.notas.NotasAdapterViendo
import com.tecsup.prototipo_proyecto.notasViendoHorizontal.NotaHorizontal
import com.tecsup.prototipo_proyecto.notasViendoHorizontal.NotasAdapterHorizontal

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_perfil)

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
        recyclerNotas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

        // Content 2
        val recyclerNotasHorizontal = findViewById<RecyclerView>(R.id.recyclerNotasViendoHorizontal)
        val listNotas2 = listOf(
            NotaHorizontal("Diseño de Interfaces", "https://img.freepik.com/fotos-premium/casa-mano-humana-fondo_488220-5956.jpg?w=996"),
            NotaHorizontal("Diseño de Interfaces", "https://img.freepik.com/fotos-premium/casa-mano-humana-fondo_488220-5956.jpg?w=996"),
            NotaHorizontal("Diseño de Interfaces", "https://img.freepik.com/fotos-premium/casa-mano-humana-fondo_488220-5956.jpg?w=996"),
            NotaHorizontal("Diseño de Interfaces", "https://img.freepik.com/fotos-premium/casa-mano-humana-fondo_488220-5956.jpg?w=996"),
            NotaHorizontal("Diseño de Interfaces", "https://img.freepik.com/fotos-premium/casa-mano-humana-fondo_488220-5956.jpg?w=996"),
            NotaHorizontal("Diseño de Interfaces", "https://img.freepik.com/fotos-premium/casa-mano-humana-fondo_488220-5956.jpg?w=996")
        )
        val adapter2 = NotasAdapterHorizontal(listNotas2)
        recyclerNotasHorizontal.adapter = adapter2
        recyclerNotasHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        //Recycler Categorias
        val recyclerCategorias = findViewById<RecyclerView>(R.id.recyclerCategorias)
        val listCategorias = listOf(
            Categoria("Finanzas", "https://img.icons8.com/?size=100&id=PQbGFaMhP8Ox&format=png&color=000000"),
            Categoria("Desarrollo Personal", "https://img.icons8.com/?size=100&id=PQbGFaMhP8Ox&format=png&color=000000"),
            Categoria("Bienes Raizes", "https://img.icons8.com/?size=100&id=PQbGFaMhP8Ox&format=png&color=000000"),
            Categoria("Finanzas", "https://img.icons8.com/?size=100&id=PQbGFaMhP8Ox&format=png&color=000000"),
            Categoria("Finanzas", "https://img.icons8.com/?size=100&id=PQbGFaMhP8Ox&format=png&color=000000"),
            Categoria("Finanzas", "https://img.icons8.com/?size=100&id=PQbGFaMhP8Ox&format=png&color=000000"),
        )

        val adapterCategoria = CategoriaAdapter(listCategorias)
        recyclerCategorias.adapter = adapterCategoria
        recyclerCategorias.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> true
                R.id.circleplay -> true
                R.id.heart -> {
                    startActivity(Intent(this, CertificadosActivity::class.java))
                    true
                }
                R.id.gato -> {
                    startActivity(Intent(this, EditarPerfilActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}