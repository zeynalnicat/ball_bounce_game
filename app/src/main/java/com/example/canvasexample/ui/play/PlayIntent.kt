package com.example.canvasexample.ui.play

sealed class PlayIntent {

    data object OnNavigateToResult : PlayIntent()
}