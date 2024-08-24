package app.noteapp.compose.notecontent.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import app.noteapp.domain.model.Note
import app.noteapp.viewmodels.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteContentTopBar (
    note: Note,
    viewModel: NoteViewModel,
    title: String,
    content: String,
    onBackClick: () -> Unit,
    noteId: Int,
    isVisible: Boolean
) {
    TopAppBar(
        title = { Text("Содержимое заметки") },
        navigationIcon = {
            IconButton(onClick = {
                backUpdate(
                    note = note,
                    viewModel = viewModel,
                    title = title,
                    content = content,
                    onBackClick = {onBackClick()}
                )
            }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = { noteDelete(
                noteId = noteId,
                onBackClick = {onBackClick()},
                viewModel = viewModel
            )
            }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
            }
            if (isVisible) {
                IconButton(
                    onClick = {
                        noteUpdate(
                            note = note,
                            viewModel = viewModel,
                            title = title,
                            content = content
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Update note"
                    )
                }
            }
        },
    )
}

fun noteDelete (
    noteId: Int,
    onBackClick: () -> Unit,
    viewModel: NoteViewModel
) {
    viewModel.deleteNote(noteId)
    onBackClick()
}

fun noteUpdate (
    note: Note,
    viewModel: NoteViewModel,
    title: String,
    content: String
) {
    note.title = title
    note.content = content
    viewModel.updateNote(note)
}

fun backUpdate(
    note: Note,
    viewModel: NoteViewModel,
    title: String,
    content: String,
    onBackClick: () -> Unit
) {
    note.title = title
    note.content = content
    viewModel.updateNote(note)
    onBackClick()
}