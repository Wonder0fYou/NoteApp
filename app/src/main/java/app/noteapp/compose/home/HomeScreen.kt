package app.noteapp.compose.home

import android.util.Log
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.noteapp.viewmodels.NoteViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.noteapp.domain.model.Note
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (
    viewModel: NoteViewModel = hiltViewModel(),
    onNoteClick: (Note) -> Unit = {},
    onAddNoteClick: () -> Unit = {}
) {
    val notesState by viewModel.notes.collectAsState()
    Log.d("HomeScreen", "notes get: ${notesState.size}")
    LaunchedEffect(key1 = true) {
        viewModel.notes
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {Text("Заметки", fontSize = 22.sp)},
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Поиск")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {onAddNoteClick()}) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Добавить заметку")
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
                .padding(8.dp)
        ) {
            items(notes) { note ->
                Card(
                    onClick = { onNoteClick(note)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    note.title?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

            }
        }
    }
}

