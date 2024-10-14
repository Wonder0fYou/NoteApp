package com.example.cftapp.presentation.note.notecontent

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cftapp.presentation.note.notecontent.components.NoteContentTopBar
import com.example.cftapp.presentation.note.notecontent.components.NotePageContent
import com.example.cftapp.presentation.viewmodels.NoteViewModel

@Composable
fun NoteContent(
    noteId: Int,
    viewModel: NoteViewModel,
    onBackClick: () -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getNote(noteId)
    }
    val note by viewModel.note.collectAsStateWithLifecycle()
    val isVisible by viewModel.isVisible.collectAsStateWithLifecycle()

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    LaunchedEffect(note) {
        title = note.title
        content = note.content
    }

    Scaffold (
        topBar = {
            NoteContentTopBar(
                note = note,
                viewModel = viewModel,
                title = title,
                content = content,
                onBackClick = { onBackClick() },
                noteId = noteId,
                isVisible = isVisible
            )
        },
        content = { paddingValues ->
            NotePageContent(
                title = title,
                content = content,
                Modifier.padding(top = paddingValues.calculateTopPadding()),
                onTitleChange = {
                    title = it
                    viewModel.onTitleChange(it)
                },
                onContentChange = {
                    content = it
                    viewModel.onContentChange(it)
                }
            )
        }
    )
}