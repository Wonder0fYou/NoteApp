package app.domain.entity

import java.util.Date

data class NoteItem(
    val noteId: Int = 0,
    var title: String,
    var content: String,
    var lastEdit: Date
)