import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tecsup.prototipo_proyecto.R
import com.tecsup.prototipo_proyecto.cursos.Curso1ViewHolder
import com.tecsup.prototipo_proyecto.cursos.CursoInscripcion

class Curso1Adapter(
    private val cursos: List<CursoInscripcion>,
    private val clickListener: (CursoInscripcion) -> Unit
) : RecyclerView.Adapter<Curso1ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Curso1ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return Curso1ViewHolder(inflater.inflate(R.layout.item_curso, parent, false))
    }

    override fun onBindViewHolder(holder: Curso1ViewHolder, position: Int) {
        val curso = cursos[position]
        holder.data(curso, clickListener)
    }

    override fun getItemCount() = cursos.size
}

