package com.example.cftapp.domain.entity

import com.example.cftapp.data.database.entity.NoteItemEntity
import com.example.cftapp.domain.toUnixTimestamp
import java.time.LocalDate
import java.util.Date

data class NoteItem(
    val noteId: Int = 0,
    var title: String,
    var content: String,
    var lastEdit: Date
)

fun NoteItem.toEntity(): NoteItemEntity {
    return NoteItemEntity(
        noteId = noteId,
        title = title,
        content = content,
        lastEdit = lastEdit.toUnixTimestamp()
    )
}