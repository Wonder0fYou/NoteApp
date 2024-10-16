package app.presentation.note.model

import app.domain.entity.NoteItem

sealed class NoteAction {
    data class InputSearchWord(
        val searchWord: String
    ): NoteAction()
    data class InputTitle(
        val inputTitle: String,
        val empty: Boolean = false,
    ): NoteAction()
    data class InputContent(
        val inputContent: String,
        val empty: Boolean = false,
    ): NoteAction()
    data class ChangeNote(
        val selectedNote: NoteItem,
        val visible: Boolean = false
    ): NoteAction()
    data class ChangeContent(
        val content: String,
        val visible: Boolean = false
    ): NoteAction()
    data class OpenDialogDelete(
        val openDialog: Boolean
    ): NoteAction()

    object SaveNote: NoteAction()
    object DeleteNote: NoteAction()
}