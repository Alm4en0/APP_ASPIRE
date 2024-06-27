package com.tecsup.prototipo_proyecto.videoscurso

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.tecsup.prototipo_proyecto.HomeActivity
import com.tecsup.prototipo_proyecto.R
import com.tecsup.prototipo_proyecto.moduloscurso.ModuloCursoActivity

class VideoCursoActivity : AppCompatActivity() {

    private var cursoId: Int = -1
    private lateinit var videoView: VideoView
    private lateinit var customMediaController: CustomMediaController
    private lateinit var txtTituloModulo: TextView
    private lateinit var txtDescripcionModulo: TextView

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

        // Recibir datos del intent
        val videoTitle = intent.getStringExtra("VIDEO_TITLE") ?: "Título por defecto"
        val videoDescription = intent.getStringExtra("VIDEO_DESCRIPTION") ?: "Descripción por defecto"
        val videoUrl = intent.getStringExtra("VIDEO_URL") ?: ""

        // Establecer título y descripción
        txtTituloModulo.text = videoTitle
        txtDescripcionModulo.text = videoDescription

        // Configurar el VideoView con la URL del video
        setupVideoView(videoUrl)
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
