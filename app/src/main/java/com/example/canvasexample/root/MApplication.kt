package com.example.canvasexample.root

import android.app.Application
import com.example.canvasexample.di.AppComponent
import com.example.canvasexample.di.AppModule
import com.example.canvasexample.di.DaggerAppComponent

//import com.example.canvasexample.di.DaggerAppComponent

class MApplication: Application() {

    lateinit var appComponent: AppComponent



    override fun onCreate() {
        appComponent = DaggerAppComponent.create()
        super.onCreate()
    }

}