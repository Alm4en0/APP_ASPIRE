package com.tecsup.tecsupapp.notas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NotasAdapter(val list:List<Nota>): RecyclerView.Adapter<NotaViewHolder>(){
    //Conectar con el ViewHolder(instancia)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NotaViewHolder(inflater, parent)
    }

    //Indicar el número de ellemtos a mostrar(Recyclerview)
    override fun getItemCount(): Int {
        return list.size
    }

    //Indicar los datos a mostra en cada item(viewHolder)
    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota= list[position]
        holder.data(nota)
    }


}