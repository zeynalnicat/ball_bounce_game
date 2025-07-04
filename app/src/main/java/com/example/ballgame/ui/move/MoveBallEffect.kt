package com.example.ballgame.ui.move

sealed class MoveBallEffect {

    data object OnFail : MoveBallEffect()
}