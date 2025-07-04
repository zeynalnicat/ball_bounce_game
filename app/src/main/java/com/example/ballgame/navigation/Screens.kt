package com.example.ballgame.navigation

import com.example.ballgame.ui.menu.MenuFragment
import com.example.ballgame.ui.move.MoveBallFragment
import com.example.ballgame.ui.play.PlayFragment
import com.example.ballgame.ui.result.ResultFragment
import com.example.ballgame.ui.touch_ball.TouchToBounceFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun PlayScreen() = FragmentScreen { PlayFragment() }
    fun MenuScreen() = FragmentScreen { MenuFragment() }
    fun ResultScreen() = FragmentScreen { ResultFragment() }
    fun BounceBallScreen() = FragmentScreen { TouchToBounceFragment() }

    fun MoveBallScreen() = FragmentScreen { MoveBallFragment() }
}