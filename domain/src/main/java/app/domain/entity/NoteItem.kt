package app.domain.entity

data class NoteItem(
    val noteId: Int = 0,
    var title: String?,
    var content: String?
)
