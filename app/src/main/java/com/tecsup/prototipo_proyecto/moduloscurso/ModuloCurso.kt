package com.tecsup.prototipo_proyecto.moduloscurso

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModuloCurso(
    val id: Int,
    val nombre: String,
    val descripcion: String?,
    val link: String,
    val duracion: String?,

): Parcelable
