package app.presentation.note.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.domain.entity.NoteItem
import app.presentation.R
import app.presentation.note.model.NoteState
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HomeContent(
    padding: PaddingValues,
    notesState: NoteState,
    onNoteClick: (NoteItem) -> Unit,
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
    ){
        if (notesState.listItems.isEmpty()) {
            Text(
                text = stringResource(id = R.string.empty_here),
                style = MaterialTheme.typography.displayLarge.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(notesState.listItems) { note ->
                Card(
                    onClick = {
                        onNoteClick(note) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RectangleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column (
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 8.dp)
                    ){
                        Text(
                            text = note.title,
                            modifier = Modifier
                                .padding(vertical = 2.dp, horizontal = 10.dp),
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                        Text(
                            text = note.content,
                            modifier = Modifier
                                .padding(vertical = 2.dp, horizontal = 10.dp),
                            maxLines = 1,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                        note.lastEdit.let {
                            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                            val formattedDate = dateFormat.format(it)
                            Text(
                                text = formattedDate,
                                modifier = Modifier
                                    .padding(vertical = 2.dp, horizontal = 10.dp),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        }
                    }
                }
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                    thickness = 1.dp
                )
            }
        }
    }
}