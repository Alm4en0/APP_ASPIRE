package com.tecsup.prototipo_proyecto.videoscurso

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.tecsup.prototipo_proyecto.R
import com.tecsup.prototipo_proyecto.R.id.customMediaController

class VideoCursoActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var customMediaController: CustomMediaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_curso)

        videoView = findViewById(R.id.videoView)
        customMediaController = findViewById(R.id.customMediaController)

        setupVideoView()
    }
    private fun setupVideoView() {
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.ejemplo2)
        videoView.setVideoURI(videoUri)

        customMediaController.setVideoView(videoView)
        customMediaController.showControls() // Mostrar los controles inicialmente

    }

}
