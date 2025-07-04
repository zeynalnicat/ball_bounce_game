package com.example.ballgame.ui.shared

sealed class CoreEffect {

    data object ShowRaised : CoreEffect()
    data object ShowDecreased : CoreEffect()
    data object OnFail : CoreEffect()
}