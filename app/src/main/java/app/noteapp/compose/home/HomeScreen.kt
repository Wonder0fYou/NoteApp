package app.noteapp.compose.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.noteapp.viewmodels.NoteViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import app.noteapp.domain.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (
    viewModel: NoteViewModel = hiltViewModel(),
    onNoteClick: (Note) -> Unit = {},
    onAddNoteClick: () -> Unit = {}
) {
    val notesState by viewModel.notes.collectAsState()

    LaunchedEffect(key1 = notesState) {

    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {Text("Заметки", fontSize = 30.sp)},
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {onAddNoteClick()}) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Note")
            }
        },
        content = { paddingValues ->
            HomePageScreen(padding = paddingValues, notes = notesState, onNoteClick = onNoteClick)
        }
    )
}

@Composable
private fun HomePageScreen (
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
                .padding(10.dp)
        ) {
            items(notes) { note ->
                Card(
                    onClick = { onNoteClick(note)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                    shape = RectangleShape,
                    border = BorderStroke(width = 1.dp , color = Color.Black)
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

            }
        }
    }
}

