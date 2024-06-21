package com.tecsup.prototipo_proyecto.Descargas

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tecsup.prototipo_proyecto.R

class DescargasViewHolder (inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_introduccion, parent, false)){

    private var txtTituloDescargas: TextView? = null


    init {
        txtTituloDescargas = itemView.findViewById(R.id.txtTituloDescarga)

    }

    fun data(nota: Descargas){
        txtTituloDescargas?.text = nota.tituloDescargas
    }

}