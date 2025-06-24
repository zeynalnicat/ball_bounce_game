package com.example.canvasexample.ui.play

sealed class PlayIntent {

    data object RaiseScore: PlayIntent()
    data object DecreaseScore: PlayIntent()
}