package com.tecsup.prototipo_proyecto.cursos

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tecsup.prototipo_proyecto.R

class Curso1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var cursoNombre: TextView
    private lateinit var cursoImagen: ImageView

    init {
        cursoNombre = itemView.findViewById(R.id.txtTituloCurso)
        cursoImagen = itemView.findViewById(R.id.imgCurso)
    }

    fun data(curso: CursoInscripcion, clickListener: (CursoInscripcion) -> Unit) {
        cursoNombre.text = curso.curso_nombre
        if (cursoImagen != null) {
            Picasso.get().load(curso.curso_imagen).into(cursoImagen)
        }

        itemView.setOnClickListener {
            clickListener(curso)
        }
    }
}
