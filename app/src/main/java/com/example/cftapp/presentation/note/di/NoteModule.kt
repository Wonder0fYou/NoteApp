package com.example.cftapp.presentation.note.di

import com.example.cftapp.domain.repository.NoteRepository
import com.example.cftapp.presentation.di.UiScope
import com.example.cftapp.presentation.viewmodels.NoteViewModel
import dagger.Module
import dagger.Provides

@Module
abstract class NoteModule {
    @Module
    companion object {
        @Provides
        @UiScope
        fun provideNoteViewModel(repository: NoteRepository): NoteViewModel {
            return NoteViewModel(repository)
        }
    }
}