package com.example.canvasexample.di

import com.example.canvasexample.MainActivity
import com.example.canvasexample.components.PlayLayouts
import com.example.canvasexample.ui.menu.MenuFragment
import com.example.canvasexample.ui.play.PlayFragment
import com.example.canvasexample.ui.result.ResultFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(playFragment: PlayFragment)
    fun inject(menuFragment: MenuFragment)
    fun inject(playLayouts: PlayLayouts)
    fun inject(resultFragment: ResultFragment)
}