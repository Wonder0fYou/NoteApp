package app.presentation.note.noteContent

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import app.presentation.note.noteContent.components.NoteContentTopBar
import app.presentation.note.noteContent.components.NotePageContent
import app.presentation.note.model.NoteAction
import app.presentation.note.model.NoteState

@Composable
fun NoteContentScreen(
    notesState: NoteState,
    onAction: (NoteAction) -> Unit,
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Scaffold (
        topBar = {
            NoteContentTopBar(
                notesState = notesState,
                onAction = onAction,
                onBackClick = { onBackClick() },
                onDeleteClick = {onDeleteClick()}
            )
        },
        content = { paddingValues ->
            NotePageContent(
                paddingValues = paddingValues,
                onAction = onAction,
                notesState = notesState
            )
        }
    )
}