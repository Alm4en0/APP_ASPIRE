package com.tecsup.prototipo_proyecto.auth

import android.content.Context
import android.content.SharedPreferences
import com.tecsup.prototipo_proyecto.network.RetrofitClient
import com.tecsup.prototipo_proyecto.network.request.LoginRequest
import com.tecsup.prototipo_proyecto.network.response.LoginResponse
import retrofit2.HttpException

class LoginRepository(private val context: Context) {

    private val retrofit = RetrofitClient().retrofit
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    suspend fun login(email: String, pass: String): Result<LoginResponse> {
        return try {
            val loginRequest = LoginRequest(email, pass)
            val response = retrofit.login(loginRequest)

            // Almacenar token en SharedPreferences
            with(sharedPreferences.edit()) {
                putString("auth_token", response.token)
                apply()
            }

            Result.success(response)
        } catch (e: HttpException) {
            if (e.code() == 401) {
                Result.failure(Exception("Credenciales incorrectas"))
            } else if (e.code() == 500) {
                Result.failure(Exception("Error Server"))
            } else {
                Result.failure(e)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logout() {
        with(sharedPreferences.edit()) {
            remove("auth_token")
            apply()
        }
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getString("auth_token", null) != null
    }
}
