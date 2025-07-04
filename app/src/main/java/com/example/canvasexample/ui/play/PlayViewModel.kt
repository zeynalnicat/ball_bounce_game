package com.example.canvasexample.ui.play

import androidx.lifecycle.ViewModel
import com.example.canvasexample.navigation.Screens
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