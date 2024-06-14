package com.tecsup.prototipo_proyecto.auth


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

        userViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

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

        userViewModel.cliente.observe(this) { usuario ->
            usuario?.let {
                Log.d("LoginActivity", "Login successful, navigating to PerfilActivity")
                startActivity(Intent(this, HomeActivity::class.java))
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