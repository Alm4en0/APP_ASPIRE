package com.tecsup.prototipo_proyecto.notasViendoHorizontal

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tecsup.prototipo_proyecto.R

class NotaViewHolderHorizontal(inflater:LayoutInflater, parent:ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_nota_viendo_horizontal, parent, false)){

    private var txtTituloNotasViendoHorizontal:TextView? = null
    private var imgURl: ImageView? = null

    init {
        txtTituloNotasViendoHorizontal = itemView.findViewById(R.id.txtTituloNotasViendoHorizontal)
        imgURl= itemView.findViewById(R.id.imgHorizontal)
    }

    fun data(nota: NotaHorizontal){
        txtTituloNotasViendoHorizontal?.text = nota.titulo
        Glide.with(imgURl!!.context).load(nota.imgHorizontal).into(imgURl!!)
    }

}