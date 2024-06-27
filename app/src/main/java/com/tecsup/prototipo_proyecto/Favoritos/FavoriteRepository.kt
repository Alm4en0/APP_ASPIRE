package com.tecsup.prototipo_proyecto.Favoritos

import kotlinx.coroutines.flow.Flow

class FavoriteRepository(private val favoriteDao: FavoriteDao) {
    val allFavorites: Flow<List<Favorite>> = favoriteDao.getAllFavorites()

    suspend fun insert(favorite: Favorite) {
        favoriteDao.insertFavorite(favorite)
    }

    suspend fun delete(favorite: Favorite) {
        favoriteDao.deleteFavorite(favorite)
    }

    suspend fun getFavoriteById(moduleId: String): Favorite? {
        return favoriteDao.getFavoriteById(moduleId)
    }
}