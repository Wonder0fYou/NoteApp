package app.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import app.noteapp.data.dao.AlarmDao
import app.noteapp.data.dao.NoteDao
import app.noteapp.domain.model.AlarmClock
import app.noteapp.domain.model.Note

/**
 * The Room database for this app
 */
@Database(
    entities = [Note::class, AlarmClock::class],
    version = 1,
    exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun alarmDao(): AlarmDao
}