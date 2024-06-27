import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tecsup.prototipo_proyecto.R
import com.tecsup.prototipo_proyecto.cursos.Curso1ViewHolder
import com.tecsup.prototipo_proyecto.cursos.CursoInscripcion

class Curso2Adapter(
    private var cursos: List<CursoInscripcion>,
    private val clickListener: (CursoInscripcion) -> Unit
) : RecyclerView.Adapter<Curso2ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Curso2ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return Curso2ViewHolder(inflater.inflate(R.layout.item_curso, parent, false))
    }

    override fun onBindViewHolder(holder: Curso2ViewHolder, position: Int) {
        val curso = cursos[position]
        holder.data(curso, clickListener)
    }

    override fun getItemCount() = cursos.size

    fun updateCursos(newCursos: List<CursoInscripcion>) {
        cursos = newCursos
        notifyDataSetChanged()
    }
}
