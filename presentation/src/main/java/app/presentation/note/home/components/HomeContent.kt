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
import androidx.compose.ui.unit.dp
import app.domain.entity.NoteItem

@Composable
fun HomeContent (
    padding: PaddingValues,
    notes: List<NoteItem>,
    onNoteClick: (NoteItem) -> Unit,
) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
    ){
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(notes) { note ->
                Card(
                    onClick = { onNoteClick(note)},
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RectangleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    note.title?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(vertical = 2.dp, horizontal = 10.dp),
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                    }
                    note.content?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(vertical = 2.dp, horizontal = 10.dp),
                            maxLines = 1,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
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