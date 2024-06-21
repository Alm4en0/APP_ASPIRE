package com.tecsup.prototipo_proyecto.categorias2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Categoria2Adapter(private val categorias: List<Categoria2>) :
    RecyclerView.Adapter<Categoria2ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Categoria2ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return Categoria2ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: Categoria2ViewHolder, position: Int) {
        val categoria2: Categoria2 = categorias[position]
        holder.data(categoria2)
    }

    override fun getItemCount(): Int {
        return 5
    }
    }