package com.tecsup.prototipo_proyecto.categorias2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tecsup.prototipo_proyecto.R
import com.tecsup.prototipo_proyecto.categorias2.Categoria2

class Categoria2ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_categoria2, parent, false)) {

    private var txtNombreCategoria : TextView? = null

    init {
        txtNombreCategoria= itemView.findViewById(R.id.btnCategoria)

    }

    fun data(categoria2: Categoria2) {
        txtNombreCategoria?.text = categoria2.nombre
    }
}