package com.example.canvasexample.ui.play

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

class PlayViewModel @Inject constructor(val router: Router): ViewModel() {

    private val _state = MutableStateFlow(ScoreHolderState())

    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PlayEffect>()

    val effect = _effect.asSharedFlow()


    fun onIntent(playIntent: PlayIntent){
        when(playIntent){
            PlayIntent.RaiseScore -> increaseScore()
            PlayIntent.DecreaseScore -> decreaseScore()
        }
    }


    private fun increaseScore(){
        viewModelScope.launch {
            _effect.emit(PlayEffect.ShowRaised)
        }
        _state.update { it.copy(score = _state.value.score + 20) }
    }

    private fun decreaseScore(){
        viewModelScope.launch {
            _effect.emit(PlayEffect.ShowDecreased)
        }
        _state.update { it.copy(score = _state.value.score - 10) }
        if(_state.value.score < 0){
            router.exit()
        }


    }
}