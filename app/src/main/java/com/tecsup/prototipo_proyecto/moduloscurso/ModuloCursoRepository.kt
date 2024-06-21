package com.tecsup.prototipo_proyecto.moduloscurso

class ModuloCursoRepository {
    fun getModulosCurso(): List<ModuloCurso> {
        return listOf(
            ModuloCurso("Introducción a Kotlin", "Aprende los conceptos básicos de Kotlin."),
            ModuloCurso("Fundamentos de Android", "Conceptos esenciales para desarrollar aplicaciones Android."),
            ModuloCurso("Interfaz de Usuario", "Cómo diseñar y crear interfaces atractivas."),
            ModuloCurso("Acceso a Datos", "Manejo de bases de datos en aplicaciones móviles."),
            ModuloCurso("Patrones de Diseño", "Patrones y mejores prácticas en el desarrollo móvil."),
            ModuloCurso("Fundamentos de Android", "Conceptos esenciales para desarrollar aplicaciones Android."),
            ModuloCurso("Interfaz de Usuario", "Cómo diseñar y crear interfaces atractivas."),
            ModuloCurso("Acceso a Datos", "Manejo de bases de datos en aplicaciones móviles."),
            ModuloCurso("Patrones de Diseño", "Patrones y mejores prácticas en el desarrollo móvil.")
        )
    }
}