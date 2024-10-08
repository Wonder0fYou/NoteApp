package app.noteapp

import android.app.Application
import app.noteapp.di.AppComponent
import app.noteapp.di.DaggerAppComponent

class NoteApplication: Application () {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(
            this,applicationContext
        )
        appComponent.inject(this)
    }
}