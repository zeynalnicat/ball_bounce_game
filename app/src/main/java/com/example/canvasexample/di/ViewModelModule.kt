package com.example.canvasexample.di

import com.example.canvasexample.ui.menu.MenuViewModel
import com.example.canvasexample.ui.play.PlayViewModel
import com.example.canvasexample.ui.shared.CoreViewModel
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
}