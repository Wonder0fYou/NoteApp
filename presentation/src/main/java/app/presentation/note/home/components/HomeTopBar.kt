package app.presentation.note.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.presentation.R
import app.presentation.note.model.NoteAction
import app.presentation.note.model.NoteState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    notesState: NoteState,
    onAction: (NoteAction) -> Unit
) {
    val topText: String = stringResource(id = if (notesState.listItems.size == 1) R.string.note else R.string.notes)
    Column {
        TopAppBar(
            title = {
                Text(
                    text = topText,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
            },
            actions = {
                TextField(
                    value = notesState.searchWord,
                    onValueChange = {onAction(NoteAction.InputSearchWord(it))},
                    label = { Text(stringResource(id = R.string.search)) },
                    singleLine = true,
                    modifier = Modifier
                        .size(width = 270.dp, height = 40.dp)
                        .padding(horizontal = 8.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(color = MaterialTheme.colorScheme.surface)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
}