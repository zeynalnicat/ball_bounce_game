package com.example.canvasexample.navigation

import com.example.canvasexample.ui.menu.MenuFragment
import com.example.canvasexample.ui.play.PlayFragment
import com.example.canvasexample.ui.result.ResultFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun PlayScreen() = FragmentScreen { PlayFragment() }
    fun MenuScreen() = FragmentScreen { MenuFragment() }
    fun ResultScreen() = FragmentScreen{ ResultFragment()}
}