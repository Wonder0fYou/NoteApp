package app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.data.Converters
import app.data.database.dao.AlarmDao
import app.data.database.dao.NoteDao
import app.data.database.entity.AlarmClockItemEntity
import app.data.database.entity.NoteItemEntity

/**
 * The Room database class for the application.
 * @property NoteItemEntity The Data Access Object (DAO) for accessing [NoteItemEntity] in the database.
 * @property AlarmClockItemEntity The Data Access Object (DAO) for accessing [AlarmClockItemEntity] in the database.
 */
@Database(
    entities = [
        NoteItemEntity::class,
        AlarmClockItemEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun alarmDao(): AlarmDao
}