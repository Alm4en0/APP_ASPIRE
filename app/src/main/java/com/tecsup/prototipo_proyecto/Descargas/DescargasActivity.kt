package com.tecsup.prototipo_proyecto.Descargas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tecsup.prototipo_proyecto.R

class DescargasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_descargas)
        val recyclerNotas = findViewById<RecyclerView>(R.id.reciclerDescargas)

        val listNotas = listOf(
            Descargas("Certificado1"),
            Descargas("Certificado2"),
            Descargas("Certificado3"),
            Descargas("Certificado4"),



        )

        val adapter = DescargasAdapter(listNotas)
        recyclerNotas.adapter = adapter
        //recyclerNotas.layoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerNotas.layoutManager= GridLayoutManager(this,1)
    }
}