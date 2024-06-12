package com.tecsup.prototipo_proyecto.curso

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tecsup.prototipo_proyecto.R

class CursosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cursos)
        val recyclerNotas = findViewById<RecyclerView>(R.id.reciclerCurso)

        val listNotas = listOf(
            Curso("Certificado1","asdasdasdasdasdasdasd","1520","Curso2"),
            Curso("Certificado2","asdasdasdasdasdasdasd","1520","Curso2"),
            Curso("Certificado3","asdasdasdasdasdasdasd","1520","Curso2"),
            Curso("Certificado4","asdasdasdasdasdasdasd","1520","Curso2"),



        )

        val adapter = CursoAdapter(listNotas)
        recyclerNotas.adapter = adapter
        //recyclerNotas.layoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerNotas.layoutManager= GridLayoutManager(this,1)
    }
}