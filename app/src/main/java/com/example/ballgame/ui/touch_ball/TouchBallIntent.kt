package com.example.ballgame.ui.touch_ball

sealed class TouchBallIntent {

    data object OnIncreaseClickCount: TouchBallIntent()
    data object OnNavigateToHome: TouchBallIntent()
    data object OnFail: TouchBallIntent()
    data object OnWin: TouchBallIntent()
    data object OnReset: TouchBallIntent()
    data class OnIncreaseVelocity(val vel: Float): TouchBallIntent()
    data class OnDecreaseVelocity(val vel:Float): TouchBallIntent()
}