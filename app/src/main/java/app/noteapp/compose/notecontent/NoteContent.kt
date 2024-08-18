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
import androidx.compose.runtime.derivedStateOf
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

    val isUpdated = remember {
        derivedStateOf {
            title != note?.title || content != note?.content
        }
    }

    if (note != null) {
        title = note!!.title.toString()
        content = note!!.content.toString()
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Содержимое заметки") },
                navigationIcon = {
                    IconButton(onClick = {
                        note?.let {
                            backUpdate(
                                note = it,
                                viewModel = viewModel,
                                title = title,
                                content = content,
                                onBackClick = {onBackClick()}
                            )
                        }
                    }) {
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
                                viewModel = viewModel,
                                title = title,
                                content = content
                            )
                        }
                    },
                        enabled = isUpdated.value
                    ) {
                        Icon(imageVector = Icons.Filled.Check, contentDescription = "Update note")
                    }
                },
            )
        },
        contentWindowInsets = WindowInsets(left = 12.dp, right = 6.dp),
    ){contentPadding ->
        note?.let {
            NotePageContent(
                title =  title,
                content = content,
                Modifier.padding(top = contentPadding.calculateTopPadding()),
                onTitleChange = {title = it},
                onContentChange = {content = it}
            )
        }
    }
}

@Composable
fun NotePageContent (
    title: String,
    content: String,
    modifier: Modifier = Modifier,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit
) {
    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = title,
            onValueChange = onTitleChange,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            )
        )
        TextField(
            modifier = Modifier
                .fillMaxSize(),
            value = content,
            onValueChange = onContentChange,
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
    viewModel: NoteViewModel,
    title: String,
    content: String
) {
    note.title = title
    note.content = content
    viewModel.updateNote(note)
}

fun backUpdate(
    note: Note,
    viewModel: NoteViewModel,
    title: String,
    content: String,
    onBackClick: () -> Unit
) {
    note.title = title
    note.content = content
    viewModel.updateNote(note)
    onBackClick()
}