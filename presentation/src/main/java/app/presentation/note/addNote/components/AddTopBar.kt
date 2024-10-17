package app.presentation.note.addNote.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import app.presentation.R
import app.presentation.note.model.NoteAction
import app.presentation.note.model.NoteState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTopBar(
    onAction: (NoteAction) -> Unit,
    onSaveClick: () -> Unit,
    onBackClick: () -> Unit,
    notesState: NoteState
) {
    TopAppBar(
        title = { Text(
            text = stringResource(id = R.string.add_note),
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onPrimary
            )
        )
        },
        navigationIcon = {
            IconButton(onClick = {onBackClick()}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        actions = {
            if (notesState.checkEmpty) {
                IconButton(onClick = {
                    onAction(NoteAction.AddNote)
                    onSaveClick()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "AddNote",
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