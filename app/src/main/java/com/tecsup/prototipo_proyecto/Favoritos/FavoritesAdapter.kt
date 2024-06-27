package com.tecsup.prototipo_proyecto.Favoritos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.tecsup.prototipo_proyecto.R

class FavoritesAdapter(
    private val onPlayClick: (Favorite) -> Unit,
    private val onFavoriteClick: (Favorite) -> Unit
) : ListAdapter<Favorite, FavoriteViewHolder>(FavoriteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorites, parent, false)
        return FavoriteViewHolder(view, onPlayClick, onFavoriteClick)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.data(getItem(position))
    }
}
