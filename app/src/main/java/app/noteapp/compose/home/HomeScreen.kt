package app.noteapp.compose.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.noteapp.viewmodels.NoteViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import app.noteapp.compose.home.components.HomeContent
import app.noteapp.compose.home.components.HomeFloatingButton
import app.noteapp.compose.home.components.HomeTopBar
import app.noteapp.domain.model.Note

@Composable
fun HomeScreen (
    viewModel: NoteViewModel = hiltViewModel(),
    onNoteClick: (Note) -> Unit = {},
    onAddNoteClick: () -> Unit = {}
) {
    val notesState by viewModel.notes.collectAsState()

    Scaffold (
        topBar = {
            HomeTopBar()
        },
        floatingActionButton = {
            HomeFloatingButton (
                onAddNoteClick = onAddNoteClick
            )
        },
        content = { paddingValues ->
            HomeContent(padding = paddingValues, notes = notesState, onNoteClick = onNoteClick)
        }
    )
}

