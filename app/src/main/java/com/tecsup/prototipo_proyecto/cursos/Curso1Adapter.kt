package com.tecsup.prototipo_proyecto.cursos

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Curso1Adapter (val list: List<Curso1>):RecyclerView.Adapter<Curso1ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Curso1ViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        return Curso1ViewHolder(inflater, parent)
    }

    //Indicar el número de ellemtos a mostrar(Recyclerview)
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Curso1ViewHolder, position: Int) {
        val nota= list[position]
        holder.data(nota)
    }




}