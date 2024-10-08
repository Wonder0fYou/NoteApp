package app.data.di

import app.data.AppMapper
import app.data.database.dao.AlarmDao
import app.data.database.dao.NoteDao
import app.data.repository.AlarmClockRepositoryImpl
import app.data.repository.NoteRepositoryImpl
import app.domain.repository.AlarmClockRepository
import app.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface RepositoryModule {
    companion object {

        @Provides
        @Singleton
        fun provideAppMapper(): AppMapper {
            return AppMapper()
        }

        @Provides
        @Singleton
        fun provideNoteRepository(
            noteDao: NoteDao,
            appMapper: AppMapper
        ): NoteRepository {
            return NoteRepositoryImpl(noteDao, appMapper)
        }

        @Provides
        @Singleton
        fun provideAlarmClockRepository(
            alarmDao: AlarmDao,
            appMapper: AppMapper
        ): AlarmClockRepository {
            return AlarmClockRepositoryImpl(alarmDao, appMapper)
        }
    }
}