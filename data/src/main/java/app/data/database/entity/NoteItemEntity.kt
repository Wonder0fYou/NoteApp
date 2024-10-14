package app.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteItemEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val noteId: Int = 0,
    var title: String,
    var content: String,
    var lastEdit: Long
)