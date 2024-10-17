package app.presentation.note.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import app.domain.entity.NoteItem
import app.presentation.note.home.components.HomeContent
import app.presentation.note.home.components.HomeFloatingButton
import app.presentation.note.home.components.HomeTopBar
import app.presentation.note.model.NoteAction
import app.presentation.note.model.NoteState

@Composable
fun HomeScreen (
    notesState: NoteState,
    onAction: (NoteAction) -> Unit,
    onNoteClick: (NoteItem) -> Unit = {},
    onAddNoteClick: () -> Unit = {},
){
    Scaffold (
        topBar = {
            HomeTopBar(
                notesState = notesState,
                onAction = onAction
            )
        },
        floatingActionButton = {
            HomeFloatingButton (
                onAddNoteClick = onAddNoteClick
            )
        },
        content = { paddingValues ->
            HomeContent(
                padding = paddingValues,
                notesState = notesState,
                onNoteClick = onNoteClick,
            )
        }
    )
}