package com.example.cftapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cftapp.domain.entity.NoteItem
import com.example.cftapp.domain.toDateFromUnixTimestamp

@Entity(tableName = "notes")
data class NoteItemEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val noteId: Int = 0,
    var title: String,
    var content: String,
    var lastEdit: Long
)

fun NoteItemEntity.toNoteItem(): NoteItem {
    return NoteItem(
        noteId = this.noteId,
        title = this.title,
        content = this.content,
        lastEdit = this.lastEdit.toDateFromUnixTimestamp()
    )
}