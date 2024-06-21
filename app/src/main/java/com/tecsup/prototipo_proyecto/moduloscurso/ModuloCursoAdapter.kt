package com.tecsup.prototipo_proyecto.moduloscurso

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tecsup.prototipo_proyecto.videoscurso.VideoCursoActivity

class ModuloCursoAdapter(private val list: List<ModuloCurso>) :
    RecyclerView.Adapter<ModuloCursoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuloCursoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ModuloCursoViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ModuloCursoViewHolder, position: Int) {
        val moduloCurso = list[position]
        holder.data(moduloCurso)

        holder.btnPlay.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, VideoCursoActivity::class.java).apply {
                putExtra("VIDEO_TITLE", moduloCurso.titulo)
                putExtra("VIDEO_DESCRIPTION", moduloCurso.descripcion)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}