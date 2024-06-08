package com.tecsup.prototipo_proyecto.onboarding.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.tecsup.prototipo_proyecto.R

class TercerScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third_screen, container, false)

        val btnFinalizar = view.findViewById<Button>(R.id.btnFinalizar)
        btnFinalizar.setOnClickListener {
            // Use findNavController() directly from the fragment
            findNavController().navigate(R.id.action_viewPagerFragment_to_inicioActivity)
            onBoardingFinished()
        }

        return view
    }

    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finalizar",true)
        editor.apply()
    }
}
