package com.tecsup.prototipo_proyecto.auth

import android.content.Context
import android.content.SharedPreferences
import com.tecsup.prototipo_proyecto.network.RetrofitClient
import com.tecsup.prototipo_proyecto.network.request.LoginRequest
import com.tecsup.prototipo_proyecto.network.response.LoginResponse
import retrofit2.HttpException

/**
 * Repository para manejar la autenticación de usuario y guardar datos en SharedPreferences.
 */
class LoginRepository(private val context: Context) {

    private val retrofit = RetrofitClient().retrofit
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    /**
     * Realiza el proceso de inicio de sesión y guarda los datos del usuario en SharedPreferences.
     */
    suspend fun login(email: String, pass: String): Result<LoginResponse> {
        return try {
            val loginRequest = LoginRequest(email, pass)
            val response = retrofit.login(loginRequest)

            // Almacenar token y otros datos del usuario en SharedPreferences
            with(sharedPreferences.edit()) {
                putString("auth_token", response.token)
                putString("username", response.username)
                putString("email", response.email)
                putString("firstName", response.firstName)
                putString("lastName", response.lastName)
                putString("fotoPerfil", response.fotoPerfil)
                apply()
            }

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

    /**
     * Elimina todos los datos del usuario al cerrar sesión.
     */
    fun logout() {
        with(sharedPreferences.edit()) {
            remove("auth_token")
            remove("username")
            remove("email")
            remove("firstName")
            remove("lastName")
            remove("fotoPerfil")
            apply()
        }
    }

    /**
     * Verifica si el usuario está actualmente logueado.
     */
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getString("auth_token", null) != null
    }

    /**
     * Obtiene el token de autenticación actualmente almacenado en SharedPreferences.
     */
    fun getAuthToken(): String? {
        return sharedPreferences.getString("auth_token", null)
    }

    /**
     * Obtiene los datos del usuario actualmente logueado desde SharedPreferences.
     */
    fun getLoggedUserData(): LoginResponse? {
        val token = sharedPreferences.getString("auth_token", null)
        val username = sharedPreferences.getString("username", null)
        val email = sharedPreferences.getString("email", null)
        val firstName = sharedPreferences.getString("firstName", null)
        val lastName = sharedPreferences.getString("lastName", null)
        val fotoPerfil = sharedPreferences.getString("fotoPerfil", null)

        return if (token != null && username != null && email != null &&
            firstName != null && lastName != null && fotoPerfil != null
        ) {
            LoginResponse(token, username, email, firstName, lastName, fotoPerfil)
        } else {
            null
        }
    }
}