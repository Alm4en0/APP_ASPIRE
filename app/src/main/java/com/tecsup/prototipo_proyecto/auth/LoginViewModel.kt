package com.tecsup.prototipo_proyecto.auth

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecsup.prototipo_proyecto.network.response.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val repository = LoginRepository()

    val userLoginError = MutableLiveData<Boolean>()
    val userLoginMsgError = MutableLiveData<String>()
    val error = MutableLiveData<String>()

    private val _client = MutableLiveData<LoginResponse?>()
    val cliente: LiveData<LoginResponse?> get() = _client

    fun login(email: String, pass: String) {
        if (email.isEmpty() || pass.isEmpty()) {
            userLoginError.value = true
            userLoginMsgError.value = "Ingrese datos"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.length < 5) {
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
                if (result.isSuccess) {
                    _client.postValue(result.getOrNull())
                    userLoginError.postValue(false)
                } else {
                    _client.postValue(null)
                    handleLoginError(result.exceptionOrNull())
                }
            } catch (e: Exception) {
                _client.postValue(null)
                handleLoginError(e)
                error.postValue(e.message)
            }
        }
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