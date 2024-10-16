package app.presentation.note.noteContent.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.presentation.note.model.NoteAction
import app.presentation.note.model.NoteState

@Composable
fun NotePageContent (
    paddingValues: PaddingValues,
    onAction: (NoteAction) -> Unit,
    notesState: NoteState
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ){
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = notesState.selectedNote.title,
            onValueChange = {
                val newSelectedNote = notesState.selectedNote.copy(title = it)
                onAction(NoteAction.ChangeNote(selectedNote = newSelectedNote, visible = true))
            },
            textStyle = MaterialTheme.typography.bodyLarge,
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        )
        TextField(
            modifier = Modifier
                .fillMaxSize(),
            value = notesState.selectedNote.content,
            onValueChange = {
                val newSelectedNote = notesState.selectedNote.copy(content = it)
                onAction(NoteAction.ChangeNote(selectedNote = newSelectedNote, visible = true))
            },
            textStyle = MaterialTheme.typography.bodyLarge,
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        )
    }
}