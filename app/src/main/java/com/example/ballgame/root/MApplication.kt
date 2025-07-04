package com.example.ballgame.root

import android.app.Application
import com.example.ballgame.di.AppComponent
import com.example.ballgame.di.DaggerAppComponent


class MApplication : Application() {

    lateinit var appComponent: AppComponent


    override fun onCreate() {
        appComponent = DaggerAppComponent.create()
        super.onCreate()
    }

}