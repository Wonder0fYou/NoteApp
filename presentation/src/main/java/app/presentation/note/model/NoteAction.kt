package app.presentation.note.model

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
    data class ChangeTitle(
        val noteTitle: String,
        val visible: Boolean = false
    ): NoteAction()
    data class ChangeContent(
        val noteContent: String,
        val visible: Boolean = false
    ): NoteAction()
    data class OpenDialogDelete(
        val openDialog: Boolean
    ): NoteAction()

    object AddNote: NoteAction()
    object UpdateNote: NoteAction()
    object DeleteNote: NoteAction()
}