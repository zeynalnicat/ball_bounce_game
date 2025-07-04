package com.example.ballgame.ui.play

sealed class PlayIntent {

    data object OnNavigateToResult : PlayIntent()
}