package com.tecsup.prototipo_proyecto.videoscurso

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tecsup.prototipo_proyecto.R

class VideoCursoActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var btnPlay: Button
    private lateinit var btnPause: Button
    private lateinit var btnFullscreen: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_curso)

        // Inicializar las vistas
        videoView = findViewById(R.id.videoView)
        btnPlay = findViewById(R.id.btnPlay)
        btnPause = findViewById(R.id.btnPause)
        btnFullscreen = findViewById(R.id.btnFullscreen)

        // Configurar el VideoView para reproducir el video
        setupVideoView()

        // Configurar los botones
        setupButtons()

        // Ajustar los márgenes de la actividad para evitar superposiciones con las barras de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.toolbarCursos)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /**
     * Configura el VideoView para reproducir el video desde la carpeta raw.
     * Muestra los controles de reproducción y comienza la reproducción.
     */
    private fun setupVideoView() {
        // Crear un objeto Uri para el video en la carpeta raw
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.videoejemplo)

        // Asignar la Uri al VideoView
        videoView.setVideoURI(videoUri)

        // Crear un controlador de medios (controles de reproducción)
        val mediaController = MediaController(this)

        // Asociar el controlador de medios al VideoView y limitar su área de influencia
        videoView.setMediaController(mediaController)
        mediaController.setAnchorView(videoView)

        // Iniciar la reproducción del video
        videoView.start()
    }

    /**
     * Configura los botones de reproducción, pausa y pantalla completa.
     */
    private var isFullScreen = false

    private fun setupButtons() {
        btnPlay.setOnClickListener {
            videoView.start()
        }

        btnPause.setOnClickListener {
            videoView.pause()
        }

        btnFullscreen.setOnClickListener {
            isFullScreen = !isFullScreen
            if (isFullScreen) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            } else {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            }
        }
    }
}
