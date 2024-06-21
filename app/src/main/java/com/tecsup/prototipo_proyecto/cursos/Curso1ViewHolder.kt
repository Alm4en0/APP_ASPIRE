package com.tecsup.prototipo_proyecto.cursos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tecsup.prototipo_proyecto.R

class Curso1ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_curso, parent, false)) {

    private var txtTitulo: TextView? = null
    private var txtTiempo: TextView? = null
    private var txtDescripcion: TextView? = null

    init {
        txtTitulo = itemView.findViewById(R.id.txtTitulo)
        txtTiempo = itemView.findViewById(R.id.txtTiempo)
        txtDescripcion = itemView.findViewById(R.id.txtDescripcion)
    }

    fun bind(curso: Curso1, clickListener: (Curso1) -> Unit) {
        txtTitulo?.text = curso.tituloCurso
        txtTiempo?.text = curso.tiempo
        txtDescripcion?.text = curso.descripcionCurso

        itemView.setOnClickListener {
            clickListener(curso)
        }
    }
}
