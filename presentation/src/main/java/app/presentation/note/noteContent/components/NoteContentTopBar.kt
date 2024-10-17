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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.presentation.R
import app.presentation.note.model.NoteAction
import app.presentation.note.model.NoteState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteContentTopBar (
    notesState: NoteState,
    onAction: (NoteAction) -> Unit,
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    TopAppBar(
        title = { Text(
            text = stringResource(id = R.string.content_of_the_note),
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onPrimary
            )
        ) },
        navigationIcon = {
            IconButton(onClick = {
                onAction(NoteAction.UpdateNote)
                onBackClick()
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
                onAction(NoteAction.OpenDialogDelete(true))
            }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            if (notesState.openDialogDelete) {
                AlertDialog(
                    onDismissRequest = { onAction(NoteAction.OpenDialogDelete(false)) },
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
                            onClick = {onAction(NoteAction.OpenDialogDelete(false))},
                            modifier = Modifier.offset(x = (-100).dp)
                        ) {
                            Text(text = stringResource(id = R.string.cancel))
                        }
                    },
                    confirmButton = {
                        Button(onClick = {
                            onAction(NoteAction.DeleteNote)
                            onDeleteClick()
                        }) {
                            Text(text = stringResource(id = R.string.ok))
                        }
                    }
                )
            }
            if (notesState.visible) {
                IconButton(
                    onClick = {
                        onAction(NoteAction.UpdateNote)
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