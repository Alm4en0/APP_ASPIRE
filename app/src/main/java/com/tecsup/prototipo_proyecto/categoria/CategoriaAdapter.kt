package com.tecsup.prototipo_proyecto.categoria

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class CategoriaAdapter (val list:List<Categoria>): RecyclerView.Adapter<CategoriaViewHolder>(){
        //Conectar con el ViewHolder(instancia)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return CategoriaViewHolder(inflater, parent)
        }

        //Indicar el número de ellemtos a mostrar(Recyclerview)
        override fun getItemCount(): Int {
            return list.size
        }

        //Indicar los datos a mostra en cada item(viewHolder)
        override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
            val nota= list[position]
            holder.data(nota)
        }


    }
