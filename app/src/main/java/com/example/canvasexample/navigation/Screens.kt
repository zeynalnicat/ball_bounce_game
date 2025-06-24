package com.example.canvasexample.navigation

import com.example.canvasexample.ui.MenuFragment
import com.example.canvasexample.ui.PlayFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun PlayScreen() = FragmentScreen { PlayFragment() }
    fun MenuScreen() = FragmentScreen { MenuFragment() }
}