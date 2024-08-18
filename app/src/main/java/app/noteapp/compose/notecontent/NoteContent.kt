package app.noteapp.compose.notecontent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.noteapp.domain.model.Note
import app.noteapp.viewmodels.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteContent(
    noteId: Int,
    viewModel: NoteViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }
    var content by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = noteId) {
        viewModel.getNote(noteId)
    }
    val note by viewModel.note.collectAsState()
    title = note?.title.toString()
    content = note?.content.toString()

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Содержимое заметки") },
                navigationIcon = {
                    IconButton(onClick = {onBackClick()}) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { noteDelete(
                        noteId = noteId,
                        onBackClick = {onBackClick()},
                        viewModel = viewModel
                    )}) {
                        Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
                    }
                    IconButton(onClick = {
                        note?.let {
                            noteUpdate(
                                note = it,
                                viewModel = viewModel
                            )
                        }
                    }) {
                        Icon(imageVector = Icons.Filled.Check, contentDescription = "Update note")
                    }
                },
            )
        },
        contentWindowInsets = WindowInsets(left = 12.dp, right = 6.dp),
    ){contentPadding ->
        note?.let {
            NotePageContent(
                Modifier.padding(top = contentPadding.calculateTopPadding()),
                note = it
            )
        }
    }
}

@Composable
fun NotePageContent (
    modifier: Modifier = Modifier,
    note: Note,
) {
    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = note.title.toString(),
            onValueChange = {note.title = it},
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            )
        )
        TextField(
            modifier = Modifier
                .fillMaxSize(),
            value = note.content.toString(),
            onValueChange = {note.content = it},
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            )
        )
    }
}

fun noteDelete (
    noteId: Int,
    onBackClick: () -> Unit,
    viewModel: NoteViewModel
) {
    viewModel.deleteNote(noteId)
    onBackClick()
}

fun noteUpdate (
    note: Note,
    viewModel: NoteViewModel
) {
    viewModel.updateNote(note)
}