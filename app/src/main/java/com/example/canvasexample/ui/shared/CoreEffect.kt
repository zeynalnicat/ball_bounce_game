package com.example.canvasexample.ui.shared

import com.example.canvasexample.ui.play.PlayEffect

sealed class CoreEffect {

    data object ShowRaised: CoreEffect()
    data object ShowDecreased: CoreEffect()
    data object NotifyNavigation: CoreEffect()
}