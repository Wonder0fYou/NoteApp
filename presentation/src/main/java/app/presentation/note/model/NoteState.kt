package app.presentation.note.model

import app.domain.entity.NoteItem
import app.presentation.utils.currentDate
import java.util.Date

data class NoteState(
    val listItems: List<NoteItem> = emptyList(),
    val inputTitle: String = "",
    val inputContent: String = "",
    var noteId: Int = 0,
    var noteTitle: String = "",
    var noteContent: String = "",
    val lastEdit: Date = currentDate,
    val checkEmpty: Boolean = false,
    val visible: Boolean = false,
    val searchWord: String = "",
    val openDialogDelete: Boolean = false
)