package com.tecsup.prototipo_proyecto.categorias2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tecsup.prototipo_proyecto.R

class Categoria2Adapter(
    private val categorias: List<Categoria2>,
    private val clickListener: (Categoria2) -> Unit
) : RecyclerView.Adapter<Categoria2ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Categoria2ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return Categoria2ViewHolder(inflater.inflate(R.layout.item_categoria2, parent, false))
    }

    override fun onBindViewHolder(holder: Categoria2ViewHolder, position: Int) {
        val categoria = categorias[position]
        holder.data(categoria, clickListener)
    }

    override fun getItemCount() = categorias.size
}