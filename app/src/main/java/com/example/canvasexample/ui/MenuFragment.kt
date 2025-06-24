package com.example.canvasexample.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.canvasexample.R
import com.example.canvasexample.databinding.FragmentMenuBinding
import com.example.canvasexample.navigation.Screens
import com.example.canvasexample.root.MApplication
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding


    @Inject
    lateinit var router: Router

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater)
        (requireActivity().application as MApplication).appComponent.inject(this)

        binding.btnPlay.setOnClickListener {
            router.navigateTo(Screens.PlayScreen())
        }
        return binding.root
    }


}