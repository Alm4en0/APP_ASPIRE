package com.tecsup.prototipo_proyecto.Favoritos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class FavoritosAdapter (val list:List<Favoritos>): RecyclerView.Adapter<FavoritosViewHolder>(){
        //Conectar con el ViewHolder(instancia)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritosViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return FavoritosViewHolder(inflater, parent)
        }

        //Indicar el número de ellemtos a mostrar(Recyclerview)
        override fun getItemCount(): Int {
            return list.size
        }

        //Indicar los datos a mostra en cada item(viewHolder)
        override fun onBindViewHolder(holder: FavoritosViewHolder, position: Int) {
            val nota= list[position]
            holder.data(nota)
        }


    }
