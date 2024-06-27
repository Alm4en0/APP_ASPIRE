package com.tecsup.prototipo_proyecto.moduloscurso

import RetrofitClient
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tecsup.prototipo_proyecto.HomeActivity
import com.tecsup.prototipo_proyecto.R
import com.tecsup.prototipo_proyecto.cursos.CursoActivity
import com.tecsup.prototipo_proyecto.network.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ModuloCursoActivity : AppCompatActivity() {

    private lateinit var recyclerModulos: RecyclerView
    private lateinit var moduloCursoAdapter: ModuloCursoAdapter
    private lateinit var apiService: ApiService
    private var cursoId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modulo_curso)

        // Configuración del Toolbar como ActionBar
        val toolbar: Toolbar = findViewById(R.id.toolbarCursos)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Módulos"

        // Recuperar el valor de cursoId desde el Intent
        cursoId = intent.getIntExtra("cursoId", -1)
        Log.d("ModuloCursoActivity", "Recibido cursoId: $cursoId")

        if (cursoId == -1) {
            finish()
        } else {
            /// Inicializar ApiService
            apiService = RetrofitClient(this).retrofit

            // Configurar el RecyclerView y cargar los módulos del curso
            setupRecyclerView()
            loadModulosCurso(cursoId)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, CursoActivity::class.java)
        intent.putExtra("cursoId", cursoId)
        intent.putExtra("currentScreen", HomeActivity.COURSE_SCREEN)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }

    private fun setupRecyclerView() {
        recyclerModulos = findViewById(R.id.reciclerModuloCurso)
        recyclerModulos.layoutManager = LinearLayoutManager(this)
        moduloCursoAdapter = ModuloCursoAdapter(emptyList())
        recyclerModulos.adapter = moduloCursoAdapter
    }

    private fun loadModulosCurso(cursoId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getModulosCurso(cursoId)
                Log.d("ModuloCursoActivity", "Curso ID: $cursoId")
                if (response.isSuccessful) {
                    response.body()?.let { modulos ->
                        // Verificar aquí que modulos no esté vacío
                        Log.d("ModuloCursoActivity", "Módulos obtenidos: $modulos")
                        withContext(Dispatchers.Main) {
                            moduloCursoAdapter.updateModulos(modulos)
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ModuloCursoActivity, "Error al obtener los módulos", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ModuloCursoActivity, "Fallo en la llamada: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}