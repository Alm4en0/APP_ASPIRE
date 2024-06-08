package com.tecsup.prototipo_proyecto.notasViendoHorizontal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NotasAdapterHorizontal(val list:List<NotaHorizontal>):
    RecyclerView.Adapter<NotaViewHolderHorizontal>(){
    //Conectar con el ViewHolder(instancia)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolderHorizontal {
        val inflater = LayoutInflater.from(parent.context)
        return NotaViewHolderHorizontal(inflater, parent)
    }

    //Indicar el número de ellemtos a mostrar(Recyclerview)
    override fun getItemCount(): Int {
        return list.size
    }

    //Indicar los datos a mostra en cada item(viewHolder)
    override fun onBindViewHolder(holder: NotaViewHolderHorizontal, position: Int) {
        val nota= list[position]
        holder.data(nota)
    }


}

