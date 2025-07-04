package com.example.canvasexample.di

import com.example.canvasexample.core.animation.Animation
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {
    private val cicerone = Cicerone.create()
    private val navigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    @Singleton
    fun provideRouter(): Router = cicerone.router

    @Provides
    @Singleton
    fun provideNavigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    @Singleton
    fun provideAnimation(): Animation = Animation()
}