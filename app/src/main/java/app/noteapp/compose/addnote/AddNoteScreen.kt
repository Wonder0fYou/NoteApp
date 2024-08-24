package app.noteapp.compose.addnote

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import app.noteapp.compose.addnote.components.AddContent
import app.noteapp.compose.addnote.components.AddTopBar
import app.noteapp.viewmodels.NoteViewModel

@Composable
fun AddNoteScreen (
    onSaveClick: () -> Unit,
    viewModel: NoteViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {

    var title by remember {
        mutableStateOf(viewModel.rememberTitle.value)
    }

    var content by remember {
        mutableStateOf(viewModel.rememberContent.value)
    }

    Scaffold (
        topBar = {
            AddTopBar(
                viewModel = viewModel,
                onSaveClick = { onSaveClick() },
                onBackClick = { onBackClick() },
                title = title,
                content = content
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
                },
                onContentChange = {
                    content = it
                    viewModel.onRememberContent(it)
                }
            )
        }
    )
}
