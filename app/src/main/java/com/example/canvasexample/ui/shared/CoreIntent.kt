package com.example.canvasexample.ui.shared

import com.example.canvasexample.ui.play.PlayIntent

sealed class CoreIntent {

    data object RaiseScore: CoreIntent()
    data object DecreaseScore: CoreIntent()
    data object OnNavigateToHome: CoreIntent()
    data object OnNavigateToPlay: CoreIntent()
}