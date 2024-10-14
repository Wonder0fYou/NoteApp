package app.presentation.note.addNote

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.presentation.note.addNote.components.AddContent
import app.presentation.note.addNote.components.AddTopBar
import app.presentation.note.NoteViewModel

@Composable
fun AddNoteScreen (
    onSaveClick: () -> Unit,
    viewModel: NoteViewModel,
    onBackClick: () -> Unit
) {
    var title by remember {
        mutableStateOf(viewModel.rememberTitle.value)
    }
    var content by remember {
        mutableStateOf(viewModel.rememberContent.value)
    }
    val checkEmpty by viewModel.checkEmpty.collectAsState()

    Scaffold (
        topBar = {
            AddTopBar(
                viewModel = viewModel,
                onSaveClick = {onSaveClick()},
                onBackClick = {onBackClick()},
                title = title,
                content = content,
                checkEmpty = checkEmpty
            )
        },
        content = { paddingValues ->
            AddContent(
                paddingValues = paddingValues,
                title = title,
                content = content,
                onTitleChange = {
                    title = it
                    viewModel.onRememberTitle(it)
                    viewModel.checkEmptyNote()
                },
                onContentChange = {
                    content = it
                    viewModel.onRememberContent(it)
                    viewModel.checkEmptyNote()
                }
            )
        }
    )
}