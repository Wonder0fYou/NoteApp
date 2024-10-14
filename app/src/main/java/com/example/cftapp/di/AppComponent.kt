package com.example.cftapp.di

import android.app.Application
import android.content.Context
import app.data.di.DatabaseModule
import app.data.di.RepositoryModule
import app.presentation.di.UiModule
import com.example.cftapp.CFTApplication
import com.example.cftapp.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [DatabaseModule::class,
        RepositoryModule::class,
        UiModule::class],
)
@Singleton
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: Application,
            @BindsInstance context: Context,
        ): AppComponent
    }
    fun inject(app: CFTApplication)
    fun inject(activity: MainActivity)
}