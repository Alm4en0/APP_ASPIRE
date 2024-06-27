package com.tecsup.prototipo_proyecto.auth

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecsup.prototipo_proyecto.network.response.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    private val _username = MutableLiveData<String>()
    val userName: LiveData<String> get() = _username

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _profileImageUrl = MutableLiveData<String>()
    val profileImageUrl: LiveData<String> get() = _profileImageUrl

    private val _firstName = MutableLiveData<String>()
    val firstName: LiveData<String> get() = _firstName

    private val _lastName = MutableLiveData<String>()
    val lastName: LiveData<String> get() = _lastName

    val userLoginError = MutableLiveData<Boolean>()
    val userLoginMsgError = MutableLiveData<String>()

    private val _client = MutableLiveData<LoginResponse?>()
    val cliente: LiveData<LoginResponse?> get() = _client

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    init {
        fetchUserData()
    }

    private fun fetchUserData() {
        val loggedUser = repository.getLoggedUserData()
        _username.value = loggedUser?.username ?: "Usuario"
        _email.value = loggedUser?.email ?: "Correo electrónico"
        _profileImageUrl.value = loggedUser?.fotoPerfil ?: "URL de imagen de perfil"
        _firstName.value = loggedUser?.firstName ?: "Nombre"
        _lastName.value = loggedUser?.lastName ?: "Apellido"
    }

    fun login(email: String, pass: String) {
        if (email.isEmpty() || pass.isEmpty()) {
            userLoginError.value = true
            userLoginMsgError.value = "Ingrese datos"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userLoginError.value = true
            userLoginMsgError.value = "Verifique su correo"
        } else if (pass.length < 5) {
            userLoginError.value = true
            userLoginMsgError.value = "Contraseña inválida"
        } else {
            loginService(email, pass)
        }
    }

    private fun loginService(email: String, pass: String) {
        viewModelScope.launch {
            try {
                val result = repository.login(email, pass)
                if (result.isSuccess) {
                    val loginResponse = result.getOrNull()
                    _client.postValue(loginResponse)
                    _error.postValue(null)
                    userLoginError.postValue(false)
                    loginResponse?.let {
                        _username.postValue(it.username)
                        _email.postValue(it.email)
                        _profileImageUrl.postValue(it.fotoPerfil)
                        _firstName.postValue(it.firstName)
                        _lastName.postValue(it.lastName)
                    }
                } else {
                    handleLoginError(result.exceptionOrNull())
                }
            } catch (e: Exception) {
                handleLoginError(e)
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
        _client.postValue(null)
        userLoginError.postValue(true)
        val errorMessage = when (val message = exception?.message) {
            null -> "Error de inicio de sesión. Inténtelo de nuevo."
            else -> when {
                message.contains("Red") -> "Error de red. Por favor, inténtelo de nuevo más tarde."
                message.contains("Contraseña") -> "Contraseña incorrecta"
                message.contains("Email") -> "Correo incorrecto"
                message.contains("Server") -> "Error en el servidor. Por favor, inténtelo de nuevo más tarde."
                else -> message
            }
        }
        userLoginMsgError.postValue(errorMessage)
        _error.postValue(errorMessage)
    }
}