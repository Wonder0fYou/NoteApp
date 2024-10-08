package app.presentation.compose.home

import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.domain.entity.NoteItem
import app.presentation.compose.home.components.HomeContent
import app.presentation.compose.home.components.HomeFloatingButton
import app.presentation.compose.home.components.HomeTopBar
import app.presentation.viewmodels.NoteViewModel

@Composable
fun HomeScreen (
    viewModel: NoteViewModel,
    onNoteClick: (NoteItem) -> Unit = {},
    onAddNoteClick: () -> Unit = {},
    drawerState: DrawerState
) {
    val notesState by viewModel.notes.collectAsState()

    Scaffold (
        topBar = {
            HomeTopBar(
                drawerState = drawerState
            )
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

