package com.tecsup.prototipo_proyecto.auth

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecsup.prototipo_proyecto.network.response.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val repository = LoginRepository()
    val userLoginError = MutableLiveData<Boolean>()
    val userLoginMsgError = MutableLiveData<String>()

    private val _client = MutableLiveData<LoginResponse?>()
    val cliente: LiveData<LoginResponse?> get() = _client

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun login(email: String, pass: String) {
        if (email.isEmpty() || pass.isEmpty()) {
            userLoginError.value = true
            userLoginMsgError.value = "Ingrese datos"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
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
                    _client.postValue(result.getOrNull())
                    _error.postValue(null)
                } else {
                    _client.postValue(null)
                    _error.postValue(result.exceptionOrNull()?.message)
                }
            } catch (e: Exception) {
                _client.postValue(null)
                _error.postValue(e.message)
            }
        }
    }

}