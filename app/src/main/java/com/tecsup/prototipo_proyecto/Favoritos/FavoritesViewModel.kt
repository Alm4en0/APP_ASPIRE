package com.tecsup.prototipo_proyecto.Favoritos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FavoritesViewModel(private val repository: FavoriteRepository) : ViewModel() {
    val allFavorites: LiveData<List<Favorite>> = repository.allFavorites.asLiveData()

    fun removeFavorite(favorite: Favorite) = viewModelScope.launch {
        repository.delete(favorite)
    }

    class Factory(private val repository: FavoriteRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FavoritesViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}