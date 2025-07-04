package com.example.ballgame.ui.menu

import androidx.lifecycle.ViewModel
import com.example.ballgame.navigation.Screens
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val router: Router) : ViewModel() {


    fun onIntent(intent: MenuIntent) {
        when (intent) {
            MenuIntent.OnNavigateToPlay -> router.navigateTo(Screens.PlayScreen())
            MenuIntent.OnNavigateToBounceBall -> router.navigateTo(Screens.BounceBallScreen())
            MenuIntent.OnNavigateToMoveBall -> router.navigateTo(Screens.MoveBallScreen())
        }
    }
}