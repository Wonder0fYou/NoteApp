package app.noteapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import app.noteapp.data.NoteDatabase
import app.noteapp.domain.repository.NoteRepository
import app.noteapp.utilities.DATABASE_NAME
import app.noteapp.data.dao.NoteDao
import app.noteapp.data.repository.NoteRepositoryImpl


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideNoteDatabase(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(context, NoteDatabase::class.java, DATABASE_NAME).build()

    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase) = noteDatabase.noteDao()

    @Provides
    fun  provideNoteRepository(noteDao: NoteDao): NoteRepository = NoteRepositoryImpl(noteDao)

}