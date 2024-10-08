package app.presentation.compose.notecontent

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.presentation.compose.notecontent.components.NoteContentTopBar
import app.presentation.compose.notecontent.components.NotePageContent
import app.presentation.viewmodels.NoteViewModel

@Composable
fun NoteContent(
    noteId: Int,
    viewModel: NoteViewModel,
    onBackClick: () -> Unit
) {

    LaunchedEffect(key1 = noteId) {
        viewModel.getNote(noteId)
    }
    val note by viewModel.note.collectAsState()
    val isVisible by viewModel.isVisible.collectAsState()

    var title by remember {
        mutableStateOf(note?.title ?: "")
    }
    var content by remember {
        mutableStateOf(note?.content ?: "")
    }

    Scaffold (
        topBar = {
            note?.let {
                NoteContentTopBar(
                    note = it,
                    viewModel = viewModel,
                    title = title,
                    content = content,
                    onBackClick = { onBackClick() },
                    noteId = noteId,
                    isVisible = isVisible
                )
            }
        },
        contentWindowInsets = WindowInsets(left = 12.dp, right = 6.dp),
        content = { paddingValues ->
            note?.let {
                NotePageContent(
                    title = title,
                    content = content,
                    Modifier.padding(top = paddingValues.calculateTopPadding()),
                    onTitleChange = {
                        title = it
                        viewModel.onTitleChange(it)},
                    onContentChange = {
                        content = it
                        viewModel.onContentChange(it)}
                )
            }
        }
    )
}