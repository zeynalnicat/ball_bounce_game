package com.example.canvasexample.ui.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.canvasexample.navigation.Screens
import com.example.canvasexample.ui.play.PlayEffect
import com.example.canvasexample.ui.play.PlayIntent
import com.example.canvasexample.ui.play.ScoreHolderState
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoreViewModel @Inject constructor(private val router: Router): ViewModel() {

    private val _state = MutableStateFlow(CoreState())

    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CoreEffect>()

    val effect = _effect.asSharedFlow()

    fun onIntent(coreIntent: CoreIntent){
        when(coreIntent){
            CoreIntent.RaiseScore -> increaseScore()
            CoreIntent.DecreaseScore -> decreaseScore()
            CoreIntent.OnNavigateToHome -> navigateToHome()
            CoreIntent.OnNavigateToPlay -> navigateToPlay()
        }
    }

    private fun navigateToHome(){
        _state.update { it.copy(score = 0) }
        router.exit()
    }

    private fun navigateToPlay(){
        _state.update { it.copy(score = 0) }
        router.replaceScreen(Screens.PlayScreen())
    }



    private fun increaseScore(){
        viewModelScope.launch {
            _effect.emit(CoreEffect.ShowRaised)
        }
        _state.update { it.copy(score = _state.value.score + 20) }
    }

    private fun decreaseScore(){
        viewModelScope.launch {
            _effect.emit(CoreEffect.ShowDecreased)
        }
        _state.update { it.copy(score = _state.value.score - 10) }
        if(_state.value.score < 0){
            viewModelScope.launch {
                _effect.emit(CoreEffect.NotifyNavigation)
            }

        }


    }
}