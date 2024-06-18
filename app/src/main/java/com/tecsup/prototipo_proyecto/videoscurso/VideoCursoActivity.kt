package com.tecsup.prototipo_proyecto.videoscurso

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.tecsup.prototipo_proyecto.R

class VideoCursoActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var btnPlay: Button
    private lateinit var btnPause: Button
    private lateinit var btnFullscreen: Button
    private lateinit var customMediaController: CustomMediaController
    private var isFullScreen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_curso)

        videoView = findViewById(R.id.videoView)
        btnPlay = findViewById(R.id.btnPlay)
        btnPause = findViewById(R.id.btnPause)
        btnFullscreen = findViewById(R.id.btnFullscreen)
        customMediaController = findViewById(R.id.customMediaController)

        setupVideoView()
        setupButtons()
    }

    private fun setupVideoView() {
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.videoejemplo)
        videoView.setVideoURI(videoUri)

        customMediaController.setVideoView(videoView)
        customMediaController.showControls() // Mostrar los controles inicialmente

        videoView.start()
    }

    private fun setupButtons() {
        btnPlay.setOnClickListener { videoView.start() }
        btnPause.setOnClickListener { videoView.pause() }
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
