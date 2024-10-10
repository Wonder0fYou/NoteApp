package app.presentation.note.addnote.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import app.domain.entity.NoteItem
import app.presentation.note.viewmodels.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTopBar(
    viewModel: NoteViewModel,
    onSaveClick: () -> Unit,
    onBackClick: () -> Unit,
    title: String,
    content: String
) {
    TopAppBar(
        title = { Text(text = "Добавить заметку") },
        navigationIcon = {
            IconButton(onClick = {onBackClick()}) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = { addNote(
                title = title,
                content = content,
                onSaveClick = { onSaveClick() },
                viewModel = viewModel
            ) }) {
                Icon(imageVector = Icons.Filled.Check, contentDescription = "AddNote")
            }
        }
    )
}

fun addNote(
    title: String,
    content: String,
    onSaveClick: () -> Unit,
    viewModel: NoteViewModel
) {
    viewModel.addNote(
        NoteItem (
            title = title,
            content = content
        )
    )
    onSaveClick()
}