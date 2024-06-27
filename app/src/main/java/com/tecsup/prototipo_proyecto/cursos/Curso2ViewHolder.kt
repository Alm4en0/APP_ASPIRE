import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tecsup.prototipo_proyecto.R
import com.tecsup.prototipo_proyecto.cursos.CursoInscripcion

class Curso2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tituloTextView: TextView
    private val descripcionTextView: TextView
    private val duracionTextView: TextView
    private val verModuloButton: Button

    init {
        tituloTextView = itemView.findViewById(R.id.txtTitulo)
        descripcionTextView = itemView.findViewById(R.id.txtDescripcion)
        duracionTextView = itemView.findViewById(R.id.txtTiempo)
        verModuloButton = itemView.findViewById(R.id.btnVerModulo)
    }

    fun data(curso: CursoInscripcion, clickListener: (CursoInscripcion) -> Unit) {
        tituloTextView.text = curso.curso_nombre
        descripcionTextView.text = curso.curso_detalles.descripcion
        duracionTextView.text = curso.curso_detalles.duracion


        // Configurar el click listener para el botón
        verModuloButton.setOnClickListener {
            clickListener(curso)
        }
    }
}