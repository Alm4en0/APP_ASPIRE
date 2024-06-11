package com.tecsup.prototipo_proyecto.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tecsup.prototipo_proyecto.PerfilActivity
import com.tecsup.prototipo_proyecto.R

class SesionActivity : AppCompatActivity() {
    private lateinit var userViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sesion)
        userViewModel =
            ViewModelProvider(this)[LoginViewModel::class.java]
        val edtCorreo = findViewById<EditText>(R.id.edtCorreo)
        val edtContrasena = findViewById<EditText>(R.id.edtContrasena)
        val btnLogin = findViewById<Button>(R.id.btnSesionPerfil)

        btnLogin.setOnClickListener {
            val correo = edtCorreo.text.toString()
            val contrasena = edtContrasena.text.toString()
            userViewModel.login(correo, contrasena)

        }

        userViewModel.cliente.observe(this) { usuario ->
            usuario?.let {
                startActivity(Intent(this,
                    PerfilActivity::class.java))
            }
        }

        userViewModel.error.observe(this) { usuario ->
            usuario?.let {
                Toast.makeText(this, it,
                    Toast.LENGTH_SHORT).show()
            }
        }

    }
}
