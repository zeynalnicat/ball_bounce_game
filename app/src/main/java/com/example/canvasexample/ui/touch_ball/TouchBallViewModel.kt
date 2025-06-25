package com.example.canvasexample.ui.touch_ball

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class TouchBallViewModel @Inject constructor(private val router: Router): ViewModel() {

    private val _state = MutableStateFlow(TouchBallState())

    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<TouchBallEffect>()

    val effect = _effect.asSharedFlow()


    fun onIntent(intent: TouchBallIntent){
        when(intent){
            TouchBallIntent.OnIncreaseClickCount -> _state.update { it.copy(clickCounter = _state.value.clickCounter+1) }
            TouchBallIntent.OnNavigateToHome -> router.exit()
            TouchBallIntent.OnFail -> { viewModelScope.launch { _effect.emit(TouchBallEffect.OnFail) }}
            TouchBallIntent.OnWin -> {viewModelScope.launch { _effect.emit(TouchBallEffect.OnWin) }}
            TouchBallIntent.OnReset -> _state.update { it.copy(clickCounter = 0, ballY = 400f) }
            is TouchBallIntent.OnDecreaseVelocity -> _state.update { it.copy(ballY = _state.value.ballY - intent.vel) }
            is TouchBallIntent.OnIncreaseVelocity -> _state.update { it.copy(ballY = _state.value.ballY + intent.vel) }
        }
    }


}