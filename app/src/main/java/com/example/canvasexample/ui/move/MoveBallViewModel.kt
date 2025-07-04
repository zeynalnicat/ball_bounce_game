package com.example.canvasexample.ui.move

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

class MoveBallViewModel @Inject constructor(private val router: Router) : ViewModel() {

    private val _state = MutableStateFlow(MoveBallState())

    private val _effect = MutableSharedFlow<MoveBallEffect>()

    val state = _state.asStateFlow()

    val effect = _effect.asSharedFlow()


    fun onIntent(intent: MoveBallIntent) {
        when (intent) {
            MoveBallIntent.OnCrossBarrier -> _state.update { it.copy(barrierCounts = _state.value.barrierCounts + 1) }
            MoveBallIntent.OnFail -> onFail()
            MoveBallIntent.OnReset -> _state.update { it.copy(barrierCounts = 0) }
            MoveBallIntent.OnNavigateHome -> router.exit()
        }
    }

    private fun onFail() {
        viewModelScope.launch {
            _effect.emit(MoveBallEffect.OnFail)
        }
    }
}