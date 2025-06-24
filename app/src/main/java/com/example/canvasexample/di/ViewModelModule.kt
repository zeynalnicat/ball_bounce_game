package com.example.canvasexample.di

import com.example.canvasexample.ui.play.PlayViewModel
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ViewModelModule {


    @Singleton
    @Provides
    fun providePlayViewModel(router: Router): PlayViewModel = PlayViewModel(router)
}