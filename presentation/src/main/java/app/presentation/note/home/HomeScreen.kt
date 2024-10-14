package app.presentation.note.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.domain.entity.NoteItem
import app.presentation.note.NoteViewModel
import app.presentation.note.home.components.HomeContent
import app.presentation.note.home.components.HomeFloatingButton
import app.presentation.note.home.components.HomeTopBar

@Composable
fun HomeScreen (
    viewModel: NoteViewModel,
    onNoteClick: (NoteItem) -> Unit = {},
    onAddNoteClick: () -> Unit = {},
){
    val notesList by viewModel.notes.collectAsState()

    Scaffold (
        topBar = {
            HomeTopBar(
                notesList = notesList,
                searchWord = {}
            )
        },
        floatingActionButton = {
            HomeFloatingButton (
                onAddNoteClick = onAddNoteClick
            )
        },
        content = { paddingValues ->
            HomeContent(padding = paddingValues, notes = notesList, onNoteClick = onNoteClick)
        }
    )
}