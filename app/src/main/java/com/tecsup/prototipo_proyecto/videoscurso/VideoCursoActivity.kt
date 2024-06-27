package com.tecsup.prototipo_proyecto.videoscurso

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.tecsup.prototipo_proyecto.AppDatabase
import com.tecsup.prototipo_proyecto.Favoritos.Favorite
import com.tecsup.prototipo_proyecto.Favoritos.FavoriteRepository
import com.tecsup.prototipo_proyecto.Favoritos.FavoritesActivity
import com.tecsup.prototipo_proyecto.HomeActivity
import com.tecsup.prototipo_proyecto.R
import com.tecsup.prototipo_proyecto.moduloscurso.ModuloCurso
import com.tecsup.prototipo_proyecto.moduloscurso.ModuloCursoActivity
import kotlinx.coroutines.launch

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
    private lateinit var favoriteRepository: FavoriteRepository
    private var isFavorite = false
    private var fromFavorites: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_curso)

        // Configuración del Toolbar como ActionBar
        val toolbar: Toolbar = findViewById(R.id.toolbarCursos)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Volver"

        videoView = findViewById(R.id.videoView)
        customMediaController = findViewById(R.id.customMediaController)
        txtTituloModulo = findViewById(R.id.txtTituloModulo)
        txtDescripcionModulo = findViewById(R.id.txtDescripcionModulo)
        txtNumeroModulo = findViewById(R.id.txtNumeroModulo)
        btnSiguienteVideo = findViewById(R.id.btnSiguienteVideo)
        val imbFavorite: ImageButton = findViewById(R.id.imbFavorite)

        val database = AppDatabase.getDatabase(applicationContext)
        favoriteRepository = FavoriteRepository(database.favoriteDao())


        // Recuperar datos del intent
        cursoId = intent.getIntExtra("cursoId", -1)
        currentPosition = intent.getIntExtra("CURRENT_POSITION", 0)
        modulos = intent.getParcelableArrayListExtra<ModuloCurso>("MODULOS_LIST") ?: emptyList()
        fromFavorites = intent.getBooleanExtra("FROM_FAVORITES", false)

        if (modulos.isNotEmpty() && currentPosition < modulos.size) {
            val currentModule = modulos[currentPosition]

            lifecycleScope.launch {
                isFavorite = favoriteRepository.getFavoriteById(currentModule.id.toString()) != null
                updateFavoriteIcon(imbFavorite)
            }

            imbFavorite.setOnClickListener {
                lifecycleScope.launch {
                    if (isFavorite) {
                        favoriteRepository.delete(Favorite(currentModule.id, currentModule.nombre, currentModule.descripcion ?: "", currentModule.link ?: ""))
                        Toast.makeText(this@VideoCursoActivity, "Eliminado de Favoritos", Toast.LENGTH_SHORT).show()
                    } else {
                        favoriteRepository.insert(Favorite(currentModule.id, currentModule.nombre, currentModule.descripcion ?: "", currentModule.link ?: ""))
                        Toast.makeText(this@VideoCursoActivity, "Añadido a Favoritos", Toast.LENGTH_SHORT).show()
                    }
                    isFavorite = !isFavorite
                    updateFavoriteIcon(imbFavorite)
                }
            }

            loadVideoData(currentPosition)
        } else {
            // Manejar el caso donde no hay módulos o el índice está fuera de rango
            Toast.makeText(this, "No se pudo cargar el módulo", Toast.LENGTH_SHORT).show()
        }


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

    private fun updateFavoriteIcon(imbFavorite: ImageButton) {
        if (isFavorite) {
            imbFavorite.setImageResource(R.drawable.icon_favorite2)
        } else {
            imbFavorite.setImageResource(R.drawable.icono_favorite)
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
        if (fromFavorites) {
            val intent = Intent(this, FavoritesActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
        } else {
            val intent = Intent(this, ModuloCursoActivity::class.java).apply {
                putExtra("cursoId", cursoId)
                putExtra("currentScreen", HomeActivity.COURSE_SCREEN)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
        }
        finish()
    }
}
