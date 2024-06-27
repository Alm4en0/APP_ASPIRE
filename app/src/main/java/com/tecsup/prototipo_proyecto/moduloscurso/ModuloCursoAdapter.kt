package com.tecsup.prototipo_proyecto.moduloscurso

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tecsup.prototipo_proyecto.R
import com.tecsup.prototipo_proyecto.videoscurso.VideoCursoActivity

class ModuloCursoAdapter(private var modulos: List<ModuloCurso>) :
    RecyclerView.Adapter<ModuloCursoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuloCursoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ModuloCursoViewHolder(inflater, parent)
    }


    override fun onBindViewHolder(holder: ModuloCursoViewHolder, position: Int) {
        val moduloCurso = modulos[position]
        holder.data(moduloCurso)

        holder.btnPlay.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, VideoCursoActivity::class.java).apply {
                putExtra("VIDEO_TITLE", moduloCurso.nombre)
                putExtra("VIDEO_DESCRIPTION", moduloCurso.descripcion ?: "Sin descripción")
                putExtra("VIDEO_URL", moduloCurso.link)
                putExtra("CURRENT_POSITION", position)
                putParcelableArrayListExtra("MODULOS_LIST", ArrayList(modulos))
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return modulos.size
    }

    fun updateModulos(newModulos: List<ModuloCurso>) {
        modulos = newModulos
        notifyDataSetChanged()
    }
}
