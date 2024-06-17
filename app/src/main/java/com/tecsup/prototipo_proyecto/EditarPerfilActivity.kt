package com.tecsup.prototipo_proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tecsup.prototipo_proyecto.cursos.CursoActivity

class EditarPerfilActivity : AppCompatActivity() {

    private var currentScreen: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)

        // Configuración del Toolbar como ActionBar
        val toolbar: Toolbar = findViewById(R.id.toolbarCursos)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Editar Perfil"

        // Recuperar el valor de currentScreen desde el Intent
        currentScreen = intent.getIntExtra("currentScreen", -1)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, PerfilActivity::class.java)
        startActivity(intent)
        finish()
    }

}
