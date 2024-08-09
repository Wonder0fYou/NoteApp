package app.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import app.noteapp.data.dao.NoteDao
import app.noteapp.domain.model.Note

/**
 * The Room database for this app
 */
@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}