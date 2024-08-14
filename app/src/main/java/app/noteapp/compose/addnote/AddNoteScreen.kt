package app.noteapp.compose.addnote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.noteapp.domain.model.Note
import app.noteapp.viewmodels.NoteViewModel

@Composable
fun AddNoteScreen (
    onSaveClick: () -> Unit,
    viewModel: NoteViewModel = hiltViewModel()
) {
    var title by remember {
        mutableStateOf("")
    }
    var content by remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = title,
            onValueChange = {title = it},
            label = { Text(text = "Заголовок")}
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = content,
            onValueChange = {content = it},
            label = { Text(text = "Содержание")}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.addNote(
                Note (
                    title = title,
                    content = content,
                )
            )
            onSaveClick()
        }) {
            Text(text = "Сохранить")
        }
    }

}
