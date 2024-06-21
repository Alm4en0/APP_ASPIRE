package com.tecsup.prototipo_proyecto.auth

import android.content.Context
import android.content.SharedPreferences
import com.tecsup.prototipo_proyecto.network.RetrofitClient
import com.tecsup.prototipo_proyecto.network.request.LoginRequest
import com.tecsup.prototipo_proyecto.network.response.LoginResponse
import retrofit2.HttpException

class LoginRepository(private val context: Context) {

    private val retrofit = RetrofitClient().retrofit
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    suspend fun login(email: String, pass: String): Result<LoginResponse> {
        return try {
            val loginRequest = LoginRequest(email, pass)
            val response = retrofit.login(loginRequest)

            // Almacenar token en SharedPreferences
            with(sharedPreferences.edit()) {
                putString("auth_token", response.token)
                apply()
            }

            // Guardar el nombre de usuario en SharedPreferences si no es nulo
            saveUsername(response.username)

            Result.success(response)
        } catch (e: HttpException) {
            when (e.code()) {
                401 -> Result.failure(Exception("Credenciales incorrectas"))
                500 -> Result.failure(Exception("Error del servidor"))
                else -> Result.failure(e)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    // Método para guardar el nombre de usuario
    fun saveUsername(username: String?) {
        username?.let {
            with(sharedPreferences.edit()) {
                putString("username", username)
                apply()
            }
        }
    }


    fun logout() {
        with(sharedPreferences.edit()) {
            remove("auth_token")
            remove("username")  // También eliminamos el nombre de usuario al cerrar sesión
            apply()
        }
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getString("auth_token", null) != null
    }

    fun getAuthToken(): String? {
        return sharedPreferences.getString("auth_token", null)
    }


    // Método para recuperar el nombre de usuario
    fun getUsername(): String? {
        return sharedPreferences.getString("username", null)
    }
}
