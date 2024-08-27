package app.noteapp.compose.notecontent.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val openDialogDelete = remember {
        mutableStateOf(false)
    }
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
            IconButton(onClick = {
                openDialogDelete.value = true
            }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
            }
            if (openDialogDelete.value) {
                AlertDialog(
                    onDismissRequest = { openDialogDelete.value = false },
                    title = { Text(text = "Подтвердите действие")},
                    text = { Text(text = "Вы действительно хотите удалить выбранную заметку?")},
                    confirmButton = { 
                        Button(onClick = {
                            noteDelete(
                                noteId = noteId,
                                onBackClick = {onBackClick()},
                                viewModel = viewModel,
                                openDialog = openDialogDelete.value
                            )
                            openDialogDelete.value = false
                        }) {
                            Text(text = "OK")
                        }
                    }
                )
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
    viewModel: NoteViewModel,
    openDialog: Boolean? = null
) {
    if (openDialog!!) {
        viewModel.deleteNote(noteId)
        onBackClick()
    }
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