package com.example.canvasexample.ui.touch_ball

sealed class TouchBallEffect {

    data object OnFail: TouchBallEffect()
    data object OnWin: TouchBallEffect()
}