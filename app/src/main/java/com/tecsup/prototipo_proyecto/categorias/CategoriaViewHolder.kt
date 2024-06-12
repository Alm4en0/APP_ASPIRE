package com.tecsup.prototipo_proyecto.categorias

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tecsup.prototipo_proyecto.R

class CategoriaViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_categoria, parent, false)) {

    private var txtNombreCategoria : TextView? = null

    init {
        txtNombreCategoria= itemView.findViewById(R.id.btnCategoria)

    }

    fun data(categoria: Categoria) {
        txtNombreCategoria?.text = categoria.nombre
    }
}
