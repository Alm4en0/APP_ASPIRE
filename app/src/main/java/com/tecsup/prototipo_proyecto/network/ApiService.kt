package com.tecsup.prototipo_proyecto.network

import com.tecsup.prototipo_proyecto.cursos.CursoInscripcion
import com.tecsup.prototipo_proyecto.moduloscurso.ModuloCurso
import com.tecsup.prototipo_proyecto.network.request.LoginRequest
import com.tecsup.prototipo_proyecto.network.response.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("login/")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @GET("user-courses/")
    suspend fun getUserCourses(): Response<List<CursoInscripcion>>

    @GET("cursos/{curso_id}/modulos/")
    suspend fun getModulosCurso(@Path("curso_id") cursoId: Int): Response<List<ModuloCurso>>

}
