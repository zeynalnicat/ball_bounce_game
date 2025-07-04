package com.example.ballgame.di

import com.example.ballgame.ui.menu.MenuViewModel
import com.example.ballgame.ui.move.MoveBallViewModel
import com.example.ballgame.ui.play.PlayViewModel
import com.example.ballgame.ui.shared.CoreViewModel
import com.example.ballgame.ui.touch_ball.TouchBallViewModel
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ViewModelModule {


    @Singleton
    @Provides
    fun providePlayViewModel(router: Router): PlayViewModel = PlayViewModel(router)

    @Singleton
    @Provides
    fun provideMenuViewModel(router: Router): MenuViewModel = MenuViewModel(router)

    @Singleton
    @Provides
    fun provideCoreViewModel(router: Router): CoreViewModel = CoreViewModel(router)

    @Singleton
    @Provides
    fun provideTouchBallViewModel(router: Router): TouchBallViewModel = TouchBallViewModel(router)

    @Singleton
    @Provides
    fun provideMoveBallViewModel(router: Router): MoveBallViewModel = MoveBallViewModel(router)
}