package com.tecsup.prototipo_proyecto.Favoritos

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey val moduleId: Int,
    val title: String,
    val description: String,
    val videoUrl: String
): Parcelable