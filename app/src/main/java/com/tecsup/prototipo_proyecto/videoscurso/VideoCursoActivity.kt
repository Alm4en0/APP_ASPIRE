package com.tecsup.prototipo_proyecto.videoscurso

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.tecsup.prototipo_proyecto.HomeActivity
import com.tecsup.prototipo_proyecto.R
import com.tecsup.prototipo_proyecto.moduloscurso.ModuloCurso
import com.tecsup.prototipo_proyecto.moduloscurso.ModuloCursoActivity

class VideoCursoActivity : AppCompatActivity() {

    private var cursoId: Int = -1
    private lateinit var videoView: VideoView
    private lateinit var customMediaController: CustomMediaController
    private lateinit var txtTituloModulo: TextView
    private lateinit var txtDescripcionModulo: TextView
    private lateinit var txtNumeroModulo: TextView
    private lateinit var btnSiguienteVideo: Button
    private var currentPosition: Int = 0
    private lateinit var modulos: List<ModuloCurso>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_curso)

        // Configuración del Toolbar como ActionBar
        val toolbar: Toolbar = findViewById(R.id.toolbarCursos)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Volver"

        // Recuperar el valor de cursoId desde el Intent
        cursoId = intent.getIntExtra("cursoId", -1)

        videoView = findViewById(R.id.videoView)
        customMediaController = findViewById(R.id.customMediaController)
        txtTituloModulo = findViewById(R.id.txtTituloModulo)
        txtDescripcionModulo = findViewById(R.id.txtDescripcionModulo)
        txtNumeroModulo = findViewById(R.id.txtNumeroModulo)
        btnSiguienteVideo = findViewById(R.id.btnSiguienteVideo)

        // Recibir datos del intent
        val videoTitle = intent.getStringExtra("VIDEO_TITLE") ?: "Título por defecto"
        val videoDescription = intent.getStringExtra("VIDEO_DESCRIPTION") ?: "Descripción por defecto"
        val videoUrl = intent.getStringExtra("VIDEO_URL") ?: ""

        // Establecer título y descripción
        txtTituloModulo.text = videoTitle
        txtDescripcionModulo.text = videoDescription

        cursoId = intent.getIntExtra("cursoId", -1)
        currentPosition = intent.getIntExtra("CURRENT_POSITION", 0)
        modulos = intent.getParcelableArrayListExtra("MODULOS_LIST") ?: emptyList()

        loadVideoData(currentPosition)
        // Configurar el VideoView con la URL del video
        setupVideoView(videoUrl)

        btnSiguienteVideo.setOnClickListener {
            if (currentPosition < modulos.size - 1) {
                currentPosition++
                loadVideoData(currentPosition)
            } else {
                // Opcional: Mostrar un mensaje de que es el último video
                Toast.makeText(this, "Este es el último video del módulo", Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun setupVideoView(videoUrl: String) {
        if (videoUrl.isNotEmpty()) {
            val uri = Uri.parse(videoUrl)
            videoView.setVideoURI(uri)
            customMediaController.setVideoView(videoView)
            customMediaController.showControls()
        } else {
            // Maneja el caso donde la URL del video es inválida o vacía
            txtDescripcionModulo.text = "URL del video no disponible."
        }
    }

    private fun loadVideoData(position: Int) {
        val moduloCurso = modulos[position]
        txtTituloModulo.text = moduloCurso.nombre
        txtDescripcionModulo.text = moduloCurso.descripcion ?: "Sin descripción"
        txtNumeroModulo.text = "Módulo ${position + 1}"
        setupVideoView(moduloCurso.link ?: "")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, ModuloCursoActivity::class.java).apply {
            putExtra("cursoId", cursoId)
            putExtra("currentScreen", HomeActivity.COURSE_SCREEN)
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        startActivity(intent)
        finish()
    }
}
