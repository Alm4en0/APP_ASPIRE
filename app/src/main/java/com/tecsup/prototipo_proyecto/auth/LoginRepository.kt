package com.tecsup.prototipo_proyecto.auth

import com.tecsup.prototipo_proyecto.network.RetrofitClient
import com.tecsup.prototipo_proyecto.network.request.LoginRequest
import com.tecsup.prototipo_proyecto.network.response.LoginResponse

class LoginRepository {

    private val retrofit = RetrofitClient().retrofit
    suspend fun login(email: String, pass: String):
            Result<LoginResponse> {
        return try {
            val loginRequest = LoginRequest(email, pass)
            val response = retrofit.login(loginRequest)
            Result.success(response)
        } catch (e: retrofit2.HttpException) { // code http error
            if (e.code() == 500) {
                Result.failure(Exception("Error Server"))
            } else {
                Result.failure(e)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

}