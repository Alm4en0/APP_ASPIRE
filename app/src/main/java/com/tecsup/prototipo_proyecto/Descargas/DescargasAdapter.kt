package com.tecsup.prototipo_proyecto.Descargas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class DescargasAdapter (val list:List<Descargas>): RecyclerView.Adapter<DescargasViewHolder>(){
        //Conectar con el ViewHolder(instancia)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DescargasViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return DescargasViewHolder(inflater, parent)
        }

        //Indicar el número de ellemtos a mostrar(Recyclerview)
        override fun getItemCount(): Int {
            return list.size
        }

        //Indicar los datos a mostra en cada item(viewHolder)
        override fun onBindViewHolder(holder: DescargasViewHolder, position: Int) {
            val nota= list[position]
            holder.data(nota)
        }


    }
