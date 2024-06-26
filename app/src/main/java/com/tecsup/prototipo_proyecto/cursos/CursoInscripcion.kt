package com.tecsup.prototipo_proyecto.cursos

data class CursoInscripcion (
    val id: Int,
    val usuario: Int,
    val curso: Int,
    val curso_nombre: String,
    val curso_imagen: String,
    val categoria_nombre: String,
    val subcategoria_nombre: String,
    val fecha_registro: String

    )