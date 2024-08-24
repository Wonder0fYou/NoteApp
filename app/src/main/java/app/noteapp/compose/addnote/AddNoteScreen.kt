package app.noteapp.compose.addnote

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import app.noteapp.compose.addnote.components.AddContent
import app.noteapp.compose.addnote.components.AddSnackBar
import app.noteapp.compose.addnote.components.AddTopBar
import app.noteapp.viewmodels.NoteViewModel
import kotlinx.coroutines.launch

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

    val snackBarHostState by viewModel.snackBarHostState
    val scope = rememberCoroutineScope()
    val popUpJoke by viewModel.popUpJoke.collectAsState()

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
        snackbarHost = {
            AddSnackBar(
                snackBarHostState
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
                    if (popUpJoke) {
                        scope.launch {
                            snackBarHostState.showSnackbar(
                                message = "Появился, значит, в Зоне Черный Сталкер",
                                duration = SnackbarDuration.Long
                            )
                        }
                    }
                },
                onContentChange = {
                    content = it
                    viewModel.onRememberContent(it)
                    viewModel.checkJoke(content = content)
                    if (popUpJoke) {
                        scope.launch {
                            snackBarHostState.showSnackbar(
                                message = "Появился, значит, в Зоне Черный Сталкер",
                                duration = SnackbarDuration.Long
                            )
                        }
                    }
                }
            )
        }
    )
}
