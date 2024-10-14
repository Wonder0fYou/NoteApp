package com.example.cftapp.presentation.note.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.cftapp.domain.entity.NoteItem
import com.example.cftapp.presentation.note.home.components.HomeContent
import com.example.cftapp.presentation.note.home.components.HomeFloatingButton
import com.example.cftapp.presentation.note.home.components.HomeTopBar
import com.example.cftapp.presentation.viewmodels.NoteViewModel

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
                notesList = notesList
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