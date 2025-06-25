package com.example.canvasexample.ui.shared

sealed class CoreEffect {

    data object ShowRaised : CoreEffect()
    data object ShowDecreased : CoreEffect()
    data object OnFail : CoreEffect()
}