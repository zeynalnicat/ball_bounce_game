package com.example.canvasexample.ui.menu


sealed class MenuIntent {

    data object OnNavigateToPlay : MenuIntent()
    data object OnNavigateToBounceBall : MenuIntent()
    data object OnNavigateToMoveBall : MenuIntent()
}