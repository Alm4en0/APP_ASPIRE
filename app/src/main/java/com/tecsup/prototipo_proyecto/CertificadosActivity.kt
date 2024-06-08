package com.tecsup.prototipo_proyecto


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tecsup.tecsupapp.notas.Nota
import com.tecsup.tecsupapp.notas.NotasAdapter

class CertificadosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_certificados)
        val recyclerNotas = findViewById<RecyclerView>(R.id.recyclerNotasCertificado)

        val listNotas = listOf(
            Nota("Certificado1"),
            Nota("Certificado1"),
            Nota("Certificado1"),
            Nota("Certificado1")



        )

        val adapter = NotasAdapter(listNotas)
        recyclerNotas.adapter = adapter
        //recyclerNotas.layoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerNotas.layoutManager= GridLayoutManager(this,1)

        val intent = Intent(this, PerfilActivity::class.java)
        val intent2 = Intent(this, EditarPerfilActivity::class.java)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    // Acción para el elemento Home
                    startActivity(Intent(this, PerfilActivity::class.java))
                    true
                }
                R.id.circleplay -> {
                    // Acción para el elemento Circle Play
                    true
                }
                R.id.heart -> {
                    // Acción para el elemento Heart
                    startActivity(Intent(this, CertificadosActivity::class.java))
                    true
                }
                R.id.gato -> {
                    // Acción para el elemento Gato
                    startActivity(Intent(this, EditarPerfilActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}