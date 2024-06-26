package com.tecsup.prototipo_proyecto.cursos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class CursoAdapter (val list:List<CursoInscripcion>): RecyclerView.Adapter<CursoViewHolder>(){
        //Conectar con el ViewHolder(instancia)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return CursoViewHolder(inflater, parent)
        }

        //Indicar el número de ellemtos a mostrar(Recyclerview)
        override fun getItemCount(): Int {
            return list.size
        }

        //Indicar los datos a mostra en cada item(viewHolder)
        override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {
            val nota= list[position]
            holder.data(nota)
        }


    }
