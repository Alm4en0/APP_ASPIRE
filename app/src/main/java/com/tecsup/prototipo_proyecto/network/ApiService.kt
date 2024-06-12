package com.tecsup.prototipo_proyecto.network

import com.tecsup.prototipo_proyecto.network.request.LoginRequest
import com.tecsup.prototipo_proyecto.network.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login/")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

}
