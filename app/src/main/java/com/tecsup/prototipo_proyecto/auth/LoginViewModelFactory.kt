import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tecsup.prototipo_proyecto.auth.LoginRepository

class LoginViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = LoginRepository(context)
        return modelClass.getConstructor(LoginRepository::class.java).newInstance(repository)
    }
}