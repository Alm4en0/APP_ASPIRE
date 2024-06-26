package com.tecsup.prototipo_proyecto.auth

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

    private  val _lastName = MutableLiveData<String>()
    val lastName: LiveData<String> get() = _lastName

    val userLoginError = MutableLiveData<Boolean>()
    val userLoginMsgError = MutableLiveData<String>()
    val error = MutableLiveData<String>()

    private val _client = MutableLiveData<LoginResponse?>()
    val cliente: LiveData<LoginResponse?> get() = _client

    init {
        fetchUserData()  // Obtener los datos del usuario en la inicialización
    }

    /**
     * Método para obtener los datos del usuario desde el repositorio.
     */
    private fun fetchUserData() {
        val loggedUser = repository.getLoggedUserData()
        _username.value = loggedUser?.username ?: "Usuario"
        _email.value = loggedUser?.email ?: "Correo electrónico"
        _profileImageUrl.value = loggedUser?.fotoPerfil ?: "URL de imagen de perfil"
        _firstName.value = loggedUser?.firstName?: "Nombre"
        _lastName.value = loggedUser?.lastName?: "Apellido"
    }

    /**
     * Método para iniciar sesión utilizando correo y contraseña.
     */
    fun login(email: String, pass: String) {
        viewModelScope.launch {
            try {
                val result = repository.login(email, pass)
                val loginResponse = result.getOrNull()
                if (loginResponse != null) {
                    _username.postValue(loginResponse.username)
                    _email.postValue(loginResponse.email)
                    _profileImageUrl.postValue(loginResponse.fotoPerfil)
                    _firstName.postValue(loginResponse.firstName)
                    _lastName.postValue(loginResponse.lastName)
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

    /**
     * Método para verificar si el usuario está actualmente logueado.
     */
    fun isLoggedIn(): Boolean {
        return repository.isLoggedIn()
    }

    /**
     * Método para cerrar sesión del usuario.
     */
    fun logout() {
        repository.logout()
    }

    /**
     * Método para manejar errores durante el proceso de inicio de sesión.
     */
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
