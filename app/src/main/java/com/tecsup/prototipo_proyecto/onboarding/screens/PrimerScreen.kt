package com.tecsup.prototipo_proyecto.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.tecsup.prototipo_proyecto.R
import android.widget.Button


class PrimerScreen : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first_screen, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        val btnSiguiente = view.findViewById<Button>(R.id.btnSiguiente)
        btnSiguiente.setOnClickListener {
            viewPager?.currentItem = 1
        }

        return view
    }


}