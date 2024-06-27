package com.tecsup.prototipo_proyecto.Favoritos

import androidx.recyclerview.widget.DiffUtil

class FavoriteDiffCallback : DiffUtil.ItemCallback<Favorite>() {
    override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
        return oldItem.moduleId == newItem.moduleId
    }

    override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
        return oldItem == newItem
    }
}