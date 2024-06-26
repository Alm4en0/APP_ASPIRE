package com.tecsup.prototipo_proyecto

import LoginViewModelFactory
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import com.tecsup.prototipo_proyecto.Favoritos.FavoritesActivity
import com.tecsup.prototipo_proyecto.auth.LoginActivity
import com.tecsup.prototipo_proyecto.auth.LoginRepository
import com.tecsup.prototipo_proyecto.auth.LoginViewModel
import com.tecsup.prototipo_proyecto.cursos.CursoActivity
import de.hdodenhof.circleimageview.CircleImageView

class PerfilActivity : AppCompatActivity() {

    private var currentScreen: Int? = null
    private lateinit var userViewModel: LoginViewModel
    private lateinit var tvUsername: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvFirstName: TextView
    private lateinit var tvLastName: TextView
    private lateinit var tvFotoPerfil: CircleImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // Configuración del Toolbar como ActionBar
        val toolbar: Toolbar = findViewById(R.id.toolbarCursos)
        setSupportActionBar(toolbar)

        // Habilitar la flecha de retroceso
        supportActionBar?.title = "Mi Perfil"

        val loginRepository = LoginRepository(this)
        val viewModelFactory = LoginViewModelFactory(applicationContext)
        userViewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]

        // Inicializar TextViews
        tvFirstName = findViewById(R.id.txtNombre)
        tvLastName = findViewById(R.id.txtApellido)
        tvEmail = findViewById(R.id.txtCorreo)
        tvFotoPerfil = findViewById(R.id.imgFotoPerfil)



        // Observar los datos del usuario y actualizar la UI
        observeUserData()

        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        btnCerrarSesion.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        val btnEditarPerfil = findViewById<Button>(R.id.btnEditarPerfil)
        btnEditarPerfil.setOnClickListener {
            val intent = Intent(this, EditarPerfilActivity::class.java)
            intent.putExtra("currentScreen", currentScreen)
            startActivity(intent)
        }

        // Recuperar el valor de currentScreen desde el Intent
        currentScreen = intent.getIntExtra("currentScreen", HomeActivity.PROFILE_SCREEN)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    startActivity(Intent(this@PerfilActivity, HomeActivity::class.java))
                    currentScreen = HomeActivity.HOME_SCREEN
                    updateBottomNavigation(bottomNav)
                    true
                }
                R.id.circleplay -> {
                    val intent = Intent(this, CursoActivity::class.java)
                    intent.putExtra("currentScreen", HomeActivity.COURSE_SCREEN)
                    startActivity(intent)
                    true
                }
                R.id.heart -> {
                    val intent = Intent(this, FavoritesActivity::class.java)
                    intent.putExtra("currentScreen", HomeActivity.FAVORITE_SCREEN)
                    startActivity(intent)
                    true
                }
                R.id.gato -> {
                    val intent = Intent(this, PerfilActivity::class.java)
                    intent.putExtra("currentScreen", HomeActivity.PROFILE_SCREEN)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        updateBottomNavigation(bottomNav)
    }

    private fun observeUserData() {

        userViewModel.email.observe(this, Observer { email ->
            tvEmail.text = email
        })

        userViewModel.firstName.observe(this, Observer{firstName ->
            tvFirstName.text = firstName
        })

        userViewModel.lastName.observe(this, Observer{lastName->
            tvLastName.text = lastName
        })

        userViewModel.profileImageUrl.observe(this, Observer { imageUrl ->
            Picasso.get()
                .load(imageUrl) // Aquí debes tener la URL de la imagen del usuario
                .placeholder(R.drawable.placeholder_image) // Opcional: imagen de carga mientras se descarga la imagen real
                .error(R.drawable.error_image) // Opcional: imagen para mostrar si hay un error al cargar la imagen
                .into(tvFotoPerfil) // Aquí imgFotoPerfil es tu ImageView donde deseas mostrar la imagen
        })
    }

    override fun onResume() {
        super.onResume()
        currentScreen = intent.getIntExtra("currentScreen", HomeActivity.PROFILE_SCREEN)
        updateBottomNavigation(findViewById(R.id.bottom_navigation))
    }

    private fun updateBottomNavigation(bottomNav: BottomNavigationView) {
        bottomNav.menu.getItem(currentScreen ?: 0).isChecked = true
        bottomNav.menu.getItem(currentScreen ?: 0).isEnabled = false
    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Cerrar Sesión")
        builder.setMessage("¿Estás seguro de que quieres cerrar sesión?")
        builder.setPositiveButton("Sí") { dialogInterface: DialogInterface, i: Int ->
            userViewModel.logout()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("Cancelar") { dialogInterface: DialogInterface, i: Int ->
            // Cancelar la acción
        }
        builder.show()
    }
}
