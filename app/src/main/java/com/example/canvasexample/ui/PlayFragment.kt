package com.example.canvasexample.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.canvasexample.R
import com.example.canvasexample.databinding.FragmentPlayBinding

class PlayFragment : Fragment() {

    private lateinit var binding: FragmentPlayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayBinding.inflate(layoutInflater)
        return binding.root
    }


}