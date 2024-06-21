package com.tecsup.prototipo_proyecto.moduloscurso

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tecsup.prototipo_proyecto.R

class ModuloCursoViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_modulo_curso, parent, false)) {

    private var txtTituloCurso: TextView? = null
    private var txtDescripcionCurso: TextView? = null
    private val tituloTextView: TextView = itemView.findViewById(R.id.txtTituloCurso)
    private val descripcionTextView: TextView = itemView.findViewById(R.id.txtDescripcionCurso)
    val btnPlay: FloatingActionButton = itemView.findViewById(R.id.btnPlay)


    init {
        txtTituloCurso = itemView.findViewById(R.id.txtTituloCurso)
        txtDescripcionCurso = itemView.findViewById(R.id.txtDescripcionCurso)
    }

    fun data(moduloCurso: ModuloCurso) {
        txtTituloCurso?.text = moduloCurso.titulo
        txtDescripcionCurso?.text = moduloCurso.descripcion
        tituloTextView.text = moduloCurso.titulo
        descripcionTextView.text = moduloCurso.descripcion
    }
}