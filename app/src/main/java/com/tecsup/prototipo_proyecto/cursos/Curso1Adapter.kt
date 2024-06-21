package com.tecsup.prototipo_proyecto.cursos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Curso1Adapter(
    private val list: List<Curso1>,
    private val clickListener: (Curso1) -> Unit
) : RecyclerView.Adapter<Curso1ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Curso1ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return Curso1ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: Curso1ViewHolder, position: Int) {
        val nota = list[position]
        holder.bind(nota, clickListener)
    }

    override fun getItemCount(): Int = list.size
}
