package com.example.cftapp.di

import android.app.Application
import android.content.Context
import com.example.cftapp.CFTApplication
import com.example.cftapp.MainActivity
import com.example.cftapp.data.di.DatabaseModule
import com.example.cftapp.data.di.RepositoryModule
import com.example.cftapp.presentation.di.UiModule
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