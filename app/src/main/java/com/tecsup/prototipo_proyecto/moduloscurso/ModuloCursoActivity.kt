package com.tecsup.prototipo_proyecto.moduloscurso

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tecsup.prototipo_proyecto.HomeActivity
import com.tecsup.prototipo_proyecto.R
import com.tecsup.prototipo_proyecto.cursos.CursoActivity

class ModuloCursoActivity : AppCompatActivity() {

    private var currentScreen: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modulo_curso)

        // Configuración del Toolbar como ActionBar
        val toolbar: Toolbar = findViewById(R.id.toolbarCursos)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Modulos"

        // Recuperar el valor de currentScreen desde el Intent
        currentScreen = intent.getIntExtra("currentScreen", -1)

        // Configurar el RecyclerView
        setupRecyclerView()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, CursoActivity::class.java)
        intent.putExtra("currentScreen", currentScreen)
        startActivity(intent)
        finish()
    }

    private fun setupRecyclerView() {
        val recyclerModulos = findViewById<RecyclerView>(R.id.reciclerModuloCurso)
        val repository = ModuloCursoRepository()
        val modulosList = repository.getModulosCurso()

        val adapter = ModuloCursoAdapter(modulosList)
        recyclerModulos.adapter = adapter
        recyclerModulos.layoutManager = LinearLayoutManager(this)
    }
}
