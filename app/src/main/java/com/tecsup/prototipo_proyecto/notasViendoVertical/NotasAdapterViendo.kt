package com.tecsup.tecsupapp.notas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tecsup.prototipo_proyecto.notasViendoVertical.NotaViewHolderViendo

class NotasAdapterViendo(val list:List<NotaViendo>): RecyclerView.Adapter<NotaViewHolderViendo>(){
    //Conectar con el ViewHolder(instancia)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolderViendo {
        val inflater = LayoutInflater.from(parent.context)
        return NotaViewHolderViendo(inflater, parent)
    }

    //Indicar el número de ellemtos a mostrar(Recyclerview)
    override fun getItemCount(): Int {
        return list.size
    }

    //Indicar los datos a mostra en cada item(viewHolder)
    override fun onBindViewHolder(holder: NotaViewHolderViendo, position: Int) {
        val nota= list[position]
        holder.data(nota)
    }


}

