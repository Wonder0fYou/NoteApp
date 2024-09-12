package app.noteapp.compose.home.components

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.noteapp.domain.model.Note

@Composable
fun HomeContent (
    padding: PaddingValues,
    notes: List<Note>,
    onNoteClick: (Note) -> Unit,
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
                        containerColor = Color.White
                    )
                ) {
                    note.title?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(vertical = 2.dp, horizontal = 10.dp),
                            fontSize = 22.sp,
                        )
                    }
                    note.content?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(vertical = 2.dp, horizontal = 10.dp),
                            maxLines = 1
                        )
                    }
                }
                HorizontalDivider(
                    color = Color.LightGray,
                    thickness = 1.dp
                )
            }
        }
    }
}