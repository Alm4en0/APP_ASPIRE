package com.tecsup.prototipo_proyecto.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tecsup.prototipo_proyecto.PerfilActivity
import com.tecsup.prototipo_proyecto.R
import com.tecsup.prototipo_proyecto.Recuperacion1Activity

class LoginActivity : AppCompatActivity() {
    private lateinit var userViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sesion)

        userViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        val edtCorreo = findViewById<EditText>(R.id.edtCorreo)
        val edtContrasena = findViewById<EditText>(R.id.edtContrasena)
        val btnLogin = findViewById<Button>(R.id.btnSesionPerfil)

        btnLogin.setOnClickListener {
            val correo = edtCorreo.text.toString()
            val contrasena = edtContrasena.text.toString()

            // Logging the input values for debugging
            Log.d("LoginActivity", "Correo: $correo, Contraseña: $contrasena")

            userViewModel.login(correo, contrasena)
        }

        val btnOlvidar= findViewById<Button>(R.id.btnOlvidar)
        btnOlvidar.setOnClickListener {
            val intent = Intent(this, Recuperacion1Activity::class.java)
            startActivity(intent)
        }

        // Observe errors and client data
        userViewModel.userLoginError.observe(this) { error ->
            if (error) {
                val errorMessage = userViewModel.userLoginMsgError.value
                Log.e("LoginActivity", "Error: $errorMessage")
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        userViewModel.cliente.observe(this) { usuario ->
            usuario?.let {
                Log.d("LoginActivity", "Login successful, navigating to PerfilActivity")
                startActivity(Intent(this, PerfilActivity::class.java))
            }
        }

        userViewModel.error.observe(this) { error ->
            error?.let {
                Log.e("LoginActivity", "Login error: $error")
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
