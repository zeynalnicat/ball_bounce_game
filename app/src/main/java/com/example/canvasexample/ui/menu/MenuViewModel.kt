package com.example.canvasexample.ui.menu

import androidx.lifecycle.ViewModel
import com.example.canvasexample.navigation.Screens
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val router: Router): ViewModel() {


    fun onIntent(intent: MenuIntent){
        when(intent){
            MenuIntent.OnNavigateToPlay -> router.navigateTo(Screens.PlayScreen())
        }
    }
}