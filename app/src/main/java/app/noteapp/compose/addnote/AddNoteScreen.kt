package app.noteapp.compose.addnote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import app.noteapp.domain.model.Note
import app.noteapp.viewmodels.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen (
    onSaveClick: () -> Unit,
    viewModel: NoteViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }
    var content by remember {
        mutableStateOf("")
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Добавить заметку")},
                navigationIcon = {
                    IconButton(onClick = {onBackClick()}) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { addNote(
                        title = title,
                        content = content,
                        onSaveClick = { onSaveClick() },
                        viewModel = viewModel
                    ) }) {
                        Icon(imageVector = Icons.Filled.Check, contentDescription = "AddNote")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ){
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = title,
                    onValueChange = {title = it},
                    label = { Text(text = "Заголовок")},
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
                    )
                )
                TextField(
                    modifier = Modifier
                        .fillMaxSize(),
                    value = content,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
                    ),
                    onValueChange = {content = it},
                    label = { Text(text = "Содержание")},
                )
            }
        }
    )
}

fun addNote(
    title: String,
    content: String,
    onSaveClick: () -> Unit,
    viewModel: NoteViewModel
) {
    viewModel.addNote(
        Note (
            title = title,
            content = content
        )
    )
    onSaveClick()
}
