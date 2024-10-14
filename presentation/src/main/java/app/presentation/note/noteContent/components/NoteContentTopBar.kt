package app.presentation.note.noteContent.components

import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.presentation.R
import app.presentation.note.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteContentTopBar (
    note: app.domain.entity.NoteItem,
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
        title = { Text(
            text = stringResource(id = R.string.content_of_the_note),
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onPrimary
            )
        ) },
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
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        actions = {
            IconButton(onClick = {
                openDialogDelete.value = true
            }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            if (openDialogDelete.value) {
                AlertDialog(
                    onDismissRequest = { openDialogDelete.value = false },
                    title = {
                        Text(
                            text = stringResource(id = R.string.confirm_the_action),
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    text = {
                        Text(
                            text = stringResource(id = R.string.want_to_delete_note),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    dismissButton = {
                        Button(
                            onClick = {openDialogDelete.value = false},
                            modifier = Modifier.offset(x = (-100).dp)
                        ) {
                            Text(text = stringResource(id = R.string.cancel))
                        }
                    },
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
                            Text(text = stringResource(id = R.string.ok))
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
                        contentDescription = "Update note",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
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
    note: app.domain.entity.NoteItem,
    viewModel: NoteViewModel,
    title: String,
    content: String
) {
    note.title = title
    note.content = content
    viewModel.updateNote(note)
}

fun backUpdate(
    note: app.domain.entity.NoteItem,
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