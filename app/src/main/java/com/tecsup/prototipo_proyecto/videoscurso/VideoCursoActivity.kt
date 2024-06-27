package com.tecsup.prototipo_proyecto.videoscurso

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.tecsup.prototipo_proyecto.R
import com.tecsup.prototipo_proyecto.moduloscurso.ModuloCursoActivity

class VideoCursoActivity : AppCompatActivity() {

    private var currentScreen: Int? = null
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

        // Recuperar el valor de currentScreen desde el Intent
        currentScreen = intent.getIntExtra("currentScreen", -1)

        videoView = findViewById(R.id.videoView)
        customMediaController = findViewById(R.id.customMediaController)
        txtTituloModulo = findViewById(R.id.txtTituloModulo)
        txtDescripcionModulo = findViewById(R.id.txtDescripcionModulo)

        // Recibir datos del intent
        val videoTitle = intent.getStringExtra("VIDEO_TITLE") ?: "Título por defecto"
        val videoDescription = intent.getStringExtra("VIDEO_DESCRIPTION") ?: "Descripción por defecto"

        // Establecer título y descripción
        txtTituloModulo.text = videoTitle
        txtDescripcionModulo.text = videoDescription

        setupVideoView()
    }

    private fun setupVideoView() {
        val videoUri = "https://drive.google.com/uc?id=1H_u8E49uJtm_MJQnfjq_vj0EQuinnH3w&export=download"
        val uri = Uri.parse(videoUri)
        videoView.setVideoURI(uri)

        videoView.start()

        customMediaController.setVideoView(videoView)
        customMediaController.showControls()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, ModuloCursoActivity::class.java)
        intent.putExtra("currentScreen", currentScreen)
        startActivity(intent)
        finish()
    }
}
