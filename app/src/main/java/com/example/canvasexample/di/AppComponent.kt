package com.example.canvasexample.di

import com.example.canvasexample.MainActivity
import com.example.canvasexample.ui.MenuFragment
import com.example.canvasexample.ui.PlayFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(playFragment: PlayFragment)
    fun inject(menuFragment: MenuFragment)
}