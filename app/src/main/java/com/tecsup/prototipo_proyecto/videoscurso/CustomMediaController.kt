package com.tecsup.prototipo_proyecto.videoscurso

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.*
import androidx.core.view.isVisible
import com.tecsup.prototipo_proyecto.R

class CustomMediaController(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {

    private var playPauseButton: ImageButton
    private var currentTimeTextView: TextView
    private var totalTimeTextView: TextView
    private var seekBar: SeekBar
    private var videoView: VideoView? = null
    private val handler = Handler(Looper.getMainLooper())

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_media_controller, this, true)

        playPauseButton = findViewById(R.id.playPauseButton)
        currentTimeTextView = findViewById(R.id.currentTimeTextView)
        totalTimeTextView = findViewById(R.id.totalTimeTextView)
        seekBar = findViewById(R.id.seekBar)

        configureControls()
    }

    fun setVideoView(vv: VideoView) {
        videoView = vv
        setupVideoViewListeners()
        updateProgress()
    }

    private fun configureControls() {
        playPauseButton.setOnClickListener {
            videoView?.let {
                if (it.isPlaying) {
                    it.pause()
                    playPauseButton.setImageResource(android.R.drawable.ic_media_play)
                } else {
                    it.start()
                    playPauseButton.setImageResource(android.R.drawable.ic_media_pause)
                }
            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    videoView?.seekTo((progress * videoView!!.duration) / seekBar!!.max)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun setupVideoViewListeners() {
        videoView?.setOnPreparedListener { mp ->
            seekBar.max = mp.duration
            totalTimeTextView.text = formatTime(mp.duration / 1000)
        }

        videoView?.setOnCompletionListener {
            currentTimeTextView.text = formatTime(0)
            seekBar.progress = 0
            playPauseButton.setImageResource(android.R.drawable.ic_media_play)
        }
    }

    private fun updateProgress() {
        handler.postDelayed({
            videoView?.let {
                if (it.isPlaying) {
                    currentTimeTextView.text = formatTime(it.currentPosition / 1000)
                    seekBar.progress = (it.currentPosition * seekBar.max) / it.duration
                }
            }
            updateProgress()
        }, 1000)
    }

    private fun formatTime(timeInSeconds: Int): String {
        val minutes = timeInSeconds / 60
        val seconds = timeInSeconds % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    fun showControls() {
        isVisible = true
    }

    fun hideControls() {
        isVisible = false
    }
}
