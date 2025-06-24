package com.example.canvasexample.ui.play

sealed class PlayEffect {

    data object ShowRaised: PlayEffect()
    data object ShowDecreased: PlayEffect()
}