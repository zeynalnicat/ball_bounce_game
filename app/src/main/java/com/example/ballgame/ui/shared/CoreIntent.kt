package com.example.ballgame.ui.shared

sealed class CoreIntent {

    data object RaiseScore : CoreIntent()
    data object DecreaseScore : CoreIntent()
    data object OnNavigateToHome : CoreIntent()
    data object OnNavigateToPlay : CoreIntent()
    data class OnSetDifficulty(val difficulty: Difficulty): CoreIntent()
    data object OnChangeVelocityX : CoreIntent()
    data object  OnChangeVelocityY: CoreIntent()
    data object OnFail : CoreIntent()
    data object OnReset: CoreIntent()
}