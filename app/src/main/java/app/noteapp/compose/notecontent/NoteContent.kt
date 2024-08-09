package app.noteapp.compose.notecontent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
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
    LaunchedEffect(key1 = noteId) {
        viewModel.getNote(noteId)
    }

    val note by viewModel.note.collectAsState()

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Содержимое заметки") },
                navigationIcon = {
                    IconButton(onClick = {onBackClick()}) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Delete, contentDescription = "Удалить")
                    }
                }
            )
        }
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
    ){
        Text(
            text = (note.title.toString())
        )

        Spacer(modifier = Modifier.height(8.dp))
        
        Text(text = (note.content.toString()))
    }
}