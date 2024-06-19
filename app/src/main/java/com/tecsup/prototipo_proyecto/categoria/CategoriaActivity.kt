package com.tecsup.prototipo_proyecto.categoria

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tecsup.prototipo_proyecto.R

class CategoriaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_categoria)
        val recyclerNotas = findViewById<RecyclerView>(R.id.reciclerCategoria)

        val listNotas = listOf(
            Categoria("Certificado1","asdasdasdasdasdasdasd"),
            Categoria("Certificado2","asdasdasdasdasdasdasd"),
            Categoria("Certificado3","asdasdasdasdasdasdasd"),
            Categoria("Certificado4","asdasdasdasdasdasdasd"),



        )

        val adapter = CategoriaAdapter(listNotas)
        recyclerNotas.adapter = adapter
        //recyclerNotas.layoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerNotas.layoutManager= GridLayoutManager(this,1)
    }
}