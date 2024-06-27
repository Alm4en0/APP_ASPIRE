package com.tecsup.prototipo_proyecto.moduloscurso

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tecsup.prototipo_proyecto.R

class ModuloCursoViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_modulo_curso, parent, false)) {

    private val txtTituloCurso: TextView
    private val txtDescripcionCurso: TextView
    val btnPlay: FloatingActionButton

    init {
        txtTituloCurso = itemView.findViewById(R.id.txtTituloModulo)
        txtDescripcionCurso = itemView.findViewById(R.id.txtDescripcionModulo)
        btnPlay = itemView.findViewById(R.id.btnPlay)
    }

    fun data(moduloCurso: ModuloCurso) {
        txtTituloCurso.text = moduloCurso.nombre
        txtDescripcionCurso.text = moduloCurso.descripcion ?: "Sin descripción"
    }
}
