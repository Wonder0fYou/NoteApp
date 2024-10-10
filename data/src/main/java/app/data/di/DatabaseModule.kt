package app.data.di

import android.content.Context
import androidx.room.Room
import app.data.DATABASE_NAME
import dagger.Provides
import app.data.database.AppDatabase
import app.data.database.dao.AlarmDao
import app.data.database.dao.NoteDao
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideAppDatabase(
        @ApplicationContext
        context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideNoteDao(appDatabase: AppDatabase): NoteDao {
        return appDatabase.noteDao()
    }

    @Provides
    fun provideAlarmDao(appDatabase: AppDatabase): AlarmDao {
        return appDatabase.alarmDao()
    }
}