package com.tecsup.prototipo_proyecto.Favoritos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tecsup.prototipo_proyecto.R

class FavoritosViewHolder (inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_favorites, parent, false)){

    private var txtTituloFavoritos: TextView? = null
    private var txtDescripcionFavoritos: TextView? = null



    init {
        txtTituloFavoritos = itemView.findViewById(R.id.txtTituloFavoritos)
        txtDescripcionFavoritos = itemView.findViewById(R.id.txtDescripcionFavoritos)
    }

    fun data(nota: Favoritos){
        txtTituloFavoritos?.text = nota.tituloFavoritos
        txtDescripcionFavoritos?.text = nota.descripcionFavoritos
    }

}