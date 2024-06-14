package com.tecsup.prototipo_proyecto.cursos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tecsup.prototipo_proyecto.R

class CursoViewHolder (inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_curso, parent, false)){

    private var txtTitulo: TextView? = null
    private var txtTiempo: TextView? = null
    private var txtnumeroCurso: TextView? = null
    private var txtDescripcion: TextView? = null


    init {
        txtTitulo = itemView.findViewById(R.id.txtTitulo)
        txtTiempo = itemView.findViewById(R.id.txtTiempo)
        txtnumeroCurso = itemView.findViewById(R.id.txtNumeroCurso)
        txtDescripcion = itemView.findViewById(R.id.txtDescripcion)

    }

    fun data(nota: Curso1){
        txtTitulo?.text = nota.tituloCurso
        txtTiempo?.text = nota.tiempo
        txtnumeroCurso?.text = nota.numeroCurso
        txtDescripcion?.text = nota.descripcionCurso
    }

}