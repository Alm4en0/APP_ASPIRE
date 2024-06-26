package com.tecsup.prototipo_proyecto.categorias2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tecsup.prototipo_proyecto.R
import com.tecsup.prototipo_proyecto.categorias2.Categoria2

class Categoria2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var categoriaNombre: Button

    init {
        categoriaNombre = itemView.findViewById(R.id.btnCategoria)
    }

    fun data(categoria: Categoria2, clickListener: (Categoria2) -> Unit) {
        categoriaNombre.text = categoria.nombre
        itemView.setOnClickListener {
            clickListener(categoria)
        }
    }
}