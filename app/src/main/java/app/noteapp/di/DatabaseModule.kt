package app.noteapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import app.noteapp.data.AppDatabase
import app.noteapp.data.dao.AlarmDao
import app.noteapp.domain.repository.NoteRepository
import app.noteapp.utilities.DATABASE_NAME
import app.noteapp.data.dao.NoteDao
import app.noteapp.data.repository.AlarmClockRepositoryImpl
import app.noteapp.data.repository.NoteRepositoryImpl
import app.noteapp.domain.repository.AlarmClockRepository


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideNoteDatabase(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()

    @Provides
    fun provideNoteDao(appDatabase: AppDatabase) = appDatabase.noteDao()

    @Provides
    fun  provideNoteRepository(noteDao: NoteDao): NoteRepository = NoteRepositoryImpl(noteDao)

    @Provides
    fun provideAlarmDao(appDatabase: AppDatabase) = appDatabase.alarmDao()

    @Provides
    fun provideAlarmClockRepository(alarmDao: AlarmDao): AlarmClockRepository = AlarmClockRepositoryImpl(alarmDao)
}