package app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import app.data.database.dao.NoteDao
import app.data.database.entity.NoteItemEntity

@Database(
    entities = [NoteItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}