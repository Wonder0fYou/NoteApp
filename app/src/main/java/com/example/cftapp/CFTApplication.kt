package com.example.cftapp

import android.app.Application
import com.example.cftapp.di.AppComponent
import com.example.cftapp.di.DaggerAppComponent


class CFTApplication: Application () {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(
            this,applicationContext
        )
        appComponent.inject(this)
    }
}