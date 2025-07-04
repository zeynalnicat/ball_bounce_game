package com.example.ballgame.di

import com.example.ballgame.MainActivity
import com.example.ballgame.components.MoveBallLayout
import com.example.ballgame.components.PlayLayouts
import com.example.ballgame.components.TouchToBounceLayout
import com.example.ballgame.ui.menu.MenuFragment
import com.example.ballgame.ui.move.MoveBallFragment
import com.example.ballgame.ui.play.PlayFragment
import com.example.ballgame.ui.result.ResultFragment
import com.example.ballgame.ui.touch_ball.TouchToBounceFragment
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
    fun inject(touchBallFragment: TouchToBounceFragment)
    fun inject(touchToBounceLayout: TouchToBounceLayout)

    fun inject(moveBallLayout: MoveBallLayout)

    fun inject(moveBallFragment: MoveBallFragment)
}