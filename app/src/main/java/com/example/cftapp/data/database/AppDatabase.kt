package com.example.cftapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cftapp.data.database.dao.NoteDao
import com.example.cftapp.data.database.entity.NoteItemEntity

@Database(
    entities = [NoteItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}