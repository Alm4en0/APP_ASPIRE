package com.tecsup.prototipo_proyecto.onboarding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.tecsup.prototipo_proyecto.R
import com.tecsup.prototipo_proyecto.onboarding.screens.PrimerScreen
import com.tecsup.prototipo_proyecto.onboarding.screens.SegundoScreen
import com.tecsup.prototipo_proyecto.onboarding.screens.TercerScreen


class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            PrimerScreen(),
            SegundoScreen(),
            TercerScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = adapter

        return view
    }
}