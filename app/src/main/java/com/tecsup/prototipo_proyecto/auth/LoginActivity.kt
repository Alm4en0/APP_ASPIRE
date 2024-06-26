// LoginActivity.kt
package com.tecsup.prototipo_proyecto.auth

import LoginViewModelFactory
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tecsup.prototipo_proyecto.HomeActivity
import com.tecsup.prototipo_proyecto.R
import com.tecsup.prototipo_proyecto.Recuperacion1Activity

class LoginActivity : AppCompatActivity() {
    private lateinit var userViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sesion)

        // Hide the ActionBar
        supportActionBar?.hide()

        val viewModelFactory = LoginViewModelFactory(applicationContext)
        userViewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]

        // Obtener el repositorio y verificar si el usuario ya está logueado
        val loginRepository = LoginRepository(this)
        if (userViewModel.isLoggedIn()) {
            val loggedUser = loginRepository.getLoggedUserData()
            loggedUser?.let {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("userName", it.username)
                startActivity(intent)
                finish()
                return
            }
        }

        val edtCorreo = findViewById<EditText>(R.id.edtCorreo)
        val edtContrasena = findViewById<EditText>(R.id.edtContrasena)
        val btnLogin = findViewById<Button>(R.id.btnSesionPerfil)

        btnLogin.setOnClickListener {
            val correo = edtCorreo.text.toString().trim()
            val contrasena = edtContrasena.text.toString().trim()

            Log.d("LoginActivity", "Correo: $correo, Contraseña: $contrasena")

            userViewModel.login(correo, contrasena)
        }

        val btnOlvidar = findViewById<Button>(R.id.btnOlvidar)
        btnOlvidar.setOnClickListener {
            val intent = Intent(this, Recuperacion1Activity::class.java)
            startActivity(intent)
        }

        userViewModel.userLoginMsgError.observe(this) { errorMessage ->
            errorMessage?.let { showErrorMessage(it) }
        }

        userViewModel.cliente.observe(this) { loginResponse ->
            loginResponse?.let {
                Log.d("LoginActivity", "Login successful, navigating to HomeActivity")
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("userName", it.username)
                startActivity(intent)
                finish()
            }
        }

        userViewModel.error.observe(this) { error ->
            error?.let { showErrorMessage(it) }
        }
    }

    private fun showErrorMessage(message: String) {
        Log.e("LoginActivity", "Error: $message")
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
