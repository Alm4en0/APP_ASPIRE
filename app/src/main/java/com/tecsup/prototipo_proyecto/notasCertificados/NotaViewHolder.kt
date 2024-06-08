package com.tecsup.tecsupapp.notas

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tecsup.prototipo_proyecto.R

class NotaViewHolder(inflater:LayoutInflater, parent:ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_nota_certificado, parent, false)){

    private var txtTituloNotas:TextView? = null

    init {
        txtTituloNotas = itemView.findViewById(R.id.txtTituloNotas)

    }

    fun data(nota: Nota){
        txtTituloNotas?.text = nota.titulo
    }

}