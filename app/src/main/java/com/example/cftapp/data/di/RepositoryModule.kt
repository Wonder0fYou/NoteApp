package com.example.cftapp.data.di

import com.example.cftapp.data.database.dao.NoteDao
import com.example.cftapp.data.repository.NoteRepositoryImpl
import com.example.cftapp.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface RepositoryModule {
    companion object {
        @Provides
        @Singleton
        fun provideNoteRepository(
            noteDao: NoteDao
        ): NoteRepository {
            return NoteRepositoryImpl(noteDao)
        }
    }
}