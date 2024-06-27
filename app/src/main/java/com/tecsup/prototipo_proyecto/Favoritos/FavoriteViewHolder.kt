package com.tecsup.prototipo_proyecto.Favoritos

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tecsup.prototipo_proyecto.R

class FavoriteViewHolder(
    itemView: View,
    private val onPlayClick: (Favorite) -> Unit,
    private val onFavoriteClick: (Favorite) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private val txtTitle: TextView = itemView.findViewById(R.id.txtTituloFavoritos)
    private val txtDescription: TextView = itemView.findViewById(R.id.txtDescripcionFavoritos)
    private val btnPlay: FloatingActionButton = itemView.findViewById(R.id.btnPlay)
    private val imbFavorite: ImageButton = itemView.findViewById(R.id.imbFavorite)

    fun data(favorite: Favorite) {
        txtTitle.text = favorite.title
        txtDescription.text = favorite.description

        btnPlay.setOnClickListener { onPlayClick(favorite) }
        imbFavorite.setOnClickListener { onFavoriteClick(favorite) }
    }
}