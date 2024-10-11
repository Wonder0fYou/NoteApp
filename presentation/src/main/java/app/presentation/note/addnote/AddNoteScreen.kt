package app.presentation.note.addnote

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.presentation.note.addnote.components.AddContent
import app.presentation.note.addnote.components.AddTopBar
import app.presentation.note.viewmodels.NoteViewModel

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
                    viewModel.checkJoke(title = title)
                },
                onContentChange = {
                    content = it
                    viewModel.onRememberContent(it)
                    viewModel.checkJoke(content = content)
                }
            )
        }
    )
}