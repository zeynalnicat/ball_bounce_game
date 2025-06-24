package com.example.canvasexample.ui.shared

sealed class CoreIntent {

    data object RaiseScore: CoreIntent()
    data object DecreaseScore: CoreIntent()
    data object OnNavigateToHome: CoreIntent()
    data object OnNavigateToPlay: CoreIntent()
}