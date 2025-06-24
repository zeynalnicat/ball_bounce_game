package com.example.canvasexample.ui.play

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.canvasexample.navigation.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayViewModel @Inject constructor(val router: Router): ViewModel() {




    fun onIntent(playIntent: PlayIntent){
        when(playIntent){
            PlayIntent.OnNavigateToResult -> {router.replaceScreen(Screens.ResultScreen())}
        }
    }

}