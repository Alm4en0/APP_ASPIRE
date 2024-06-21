package com.tecsup.prototipo_proyecto.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecsup.prototipo_proyecto.network.response.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    private var authToken: String? = null
    private val _username = MutableLiveData<String>()
    val userName: LiveData<String> get() = _username
    val userLoginError = MutableLiveData<Boolean>()
    val userLoginMsgError = MutableLiveData<String>()
    val error = MutableLiveData<String>()

    private val _client = MutableLiveData<LoginResponse?>()
    val cliente: LiveData<LoginResponse?> get() = _client

    init {
        fetchUsername()  // Obtener el nombre del usuario en la inicialización
    }

    private fun fetchUsername() {
        _username.value = repository.getUsername() ?: "Usuario"
    }

    fun login(email: String, pass: String) {
        if (email.isEmpty() || pass.isEmpty()) {
            userLoginError.value = true
            userLoginMsgError.value = "Ingrese datos"
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.length < 5) {
            userLoginError.value = true
            userLoginMsgError.value = "Verifique su correo"
        } else if (pass.length < 8) {
            userLoginError.value = true
            userLoginMsgError.value = "Contraseña debe tener al menos 8 caracteres"
        } else {
            loginService(email, pass)
        }
    }

    private fun loginService(email: String, pass: String) {
        viewModelScope.launch {
            try {
                val result = repository.login(email, pass)
                val loginResponse = result.getOrNull()
                if (loginResponse != null) {
                    authToken = loginResponse.token
                    repository.saveUsername(loginResponse.username)  // Guardar el nombre de usuario
                    _username.postValue(loginResponse.username)      // Actualizar el LiveData
                    userLoginError.postValue(false)
                    _client.postValue(loginResponse)
                } else {
                    handleLoginError(result.exceptionOrNull())
                }
            } catch (e: Exception) {
                handleLoginError(e)
                error.postValue(e.message)
            }
        }
    }


    fun isLoggedIn(): Boolean {
        return repository.isLoggedIn()
    }

    fun logout() {
        repository.logout()
    }

    private fun handleLoginError(exception: Throwable?) {
        userLoginError.postValue(true)
        userLoginMsgError.postValue(
            when (val message = exception?.message) {
                null -> "Error de inicio de sesión. Inténtelo de nuevo."
                else -> when {
                    message.contains("Red") -> "Error de red. Por favor, inténtelo de nuevo más tarde."
                    message.contains("Contraseña") -> "Contraseña incorrecta"
                    message.contains("Usuario") -> "Correo incorrecto"
                    message.contains("Server") -> "Error en el servidor. Por favor, inténtelo de nuevo más tarde."
                    else -> message
                }
            }
        )
    }
}
