package com.example.ballgame.ui.move

sealed class MoveBallIntent {

    data object OnCrossBarrier : MoveBallIntent()
    data object OnFail : MoveBallIntent()
    data object OnReset : MoveBallIntent()

    data object OnNavigateHome : MoveBallIntent()
}