package com.example.ballgame.ui.play

import androidx.lifecycle.ViewModel
import com.example.ballgame.navigation.Screens
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class PlayViewModel @Inject constructor(val router: Router) : ViewModel() {


    fun onIntent(playIntent: PlayIntent) {
        when (playIntent) {
            PlayIntent.OnNavigateToResult -> {
                router.replaceScreen(Screens.ResultScreen())
            }
        }
    }

}