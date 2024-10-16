package app.presentation.note.model

import app.domain.entity.NoteItem
import app.presentation.utils.currentDate
import java.util.Date

data class NoteState(
    val listItems: List<NoteItem> = emptyList(),
    var inputTitle: String = "",
    var inputContent: String = "",
    var title: String = "",
    var content: String = "",
    val lastEdit: Date = currentDate,
    val checkEmpty: Boolean = false,
    val visible: Boolean = false,
    val searchWord: String = "",
    val isAddItem: Boolean = true,
    val selectedNote: NoteItem = NoteItem(0,"","", currentDate),
    val openDialogDelete: Boolean = false
)
