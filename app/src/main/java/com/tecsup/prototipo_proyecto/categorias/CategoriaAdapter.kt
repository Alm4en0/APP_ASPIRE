package com.tecsup.prototipo_proyecto.categorias

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tecsup.prototipo_proyecto.R

class CategoriaAdapter(private val categorias: List<Categoria>) :
    RecyclerView.Adapter<CategoriaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoriaViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoria: Categoria = categorias[position]
        holder.data(categoria)
    }

    override fun getItemCount(): Int {
        return 5
    }
}
