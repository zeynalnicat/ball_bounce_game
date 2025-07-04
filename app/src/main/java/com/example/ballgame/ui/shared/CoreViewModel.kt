package com.example.ballgame.ui.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ballgame.navigation.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoreViewModel @Inject constructor(private val router: Router) : ViewModel() {

    private val _state = MutableStateFlow(CoreState())

    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CoreEffect>()

    val effect = _effect.asSharedFlow()

    fun onIntent(coreIntent: CoreIntent) {
        when (coreIntent) {
            CoreIntent.RaiseScore -> increaseScore()
            CoreIntent.DecreaseScore -> decreaseScore()
            CoreIntent.OnNavigateToHome -> navigateToHome()
            CoreIntent.OnNavigateToPlay -> navigateToPlay()
            is CoreIntent.OnSetDifficulty -> setDifficultyStates(coreIntent.difficulty)
            CoreIntent.OnChangeVelocityX -> _state.update { it.copy(velocityX = - _state.value.velocityX) }
            CoreIntent.OnChangeVelocityY -> _state.update { it.copy(velocityY = - _state.value.velocityY) }
            CoreIntent.OnFail -> viewModelScope.launch { _effect.emit(CoreEffect.OnFail) }
            CoreIntent.OnReset -> _state.update { it.copy(score = 0) }
        }
    }

    private fun setDifficultyStates(difficulty: Difficulty){
        when(difficulty){
            Difficulty.EASY -> {
                _state.update { it.copy(ballRadius = 200f, paddleWidth = 300f, velocityX = 15f, velocityY = 20f) }
            }
            Difficulty.MEDIUM -> _state.update { it.copy(ballRadius = 100f, paddleWidth = 200f, velocityX = 20f, velocityY = 25f) }
            Difficulty.HARD -> _state.update { it.copy(ballRadius = 50f, paddleWidth = 150f, velocityY = 30f, velocityX = 30f) }
        }
    }


    private fun navigateToHome() {
        _state.update { it.copy(score = 0) }
        router.exit()
    }

    private fun navigateToPlay() {
        _state.update { it.copy(score = 0) }
        router.replaceScreen(Screens.PlayScreen())
    }


    private fun increaseScore() {
        viewModelScope.launch {
            _effect.emit(CoreEffect.ShowRaised)
        }
        _state.update { it.copy(score = _state.value.score + 20) }
    }

    private fun decreaseScore() {
        viewModelScope.launch {
            _effect.emit(CoreEffect.ShowDecreased)
        }
        _state.update { it.copy(score = _state.value.score - 10) }
        if (_state.value.score < 0) {
            viewModelScope.launch {
                _effect.emit(CoreEffect.OnFail)
            }

        }


    }
}