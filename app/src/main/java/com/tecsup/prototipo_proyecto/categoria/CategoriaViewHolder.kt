package com.tecsup.prototipo_proyecto.categoria

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tecsup.prototipo_proyecto.R

class CategoriaViewHolder (inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_categoria, parent, false)){

    private var txtTituloCategoria: TextView? = null
    private var txtDescripcionCategoria: TextView? = null


    init {
        txtTituloCategoria = itemView.findViewById(R.id.txtTituloCategoria)
        txtDescripcionCategoria = itemView.findViewById(R.id.txtDescripcionCategoria)

    }

    fun data(nota: Categoria){
        txtTituloCategoria?.text = nota.tituloCategoria
        txtDescripcionCategoria?.text = nota.descripcioncategoria
    }

}