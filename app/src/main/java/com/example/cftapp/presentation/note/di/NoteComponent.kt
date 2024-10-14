package com.example.cftapp.presentation.note.di

import com.example.cftapp.presentation.di.UiScope
import dagger.Subcomponent

@Subcomponent(
    modules = [NoteModule::class],
)
@UiScope
interface NoteComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(
        ): NoteComponent
    }
}