package app.presentation.note.notecontent

import android.util.Log
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.presentation.note.notecontent.components.NoteContentTopBar
import app.presentation.note.notecontent.components.NotePageContent
import app.presentation.note.viewmodels.NoteViewModel

@Composable
fun NoteContent(
    noteId: Int,
    viewModel: NoteViewModel,
    onBackClick: () -> Unit
) {

    LaunchedEffect(key1 = noteId) {
        viewModel.getNote(noteId)
    }
    val note by viewModel.note.collectAsStateWithLifecycle()
    val isVisible by viewModel.isVisible.collectAsStateWithLifecycle()

    var title by remember {
        mutableStateOf(note?.title ?: "")
    }
    Log.d("NoteContent","${title}")
    var content by remember {
        mutableStateOf(note?.content ?: "")
    }
    Log.d("NoteContent","${content}")

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
                        viewModel.onTitleChange(it)
                    },
                    onContentChange = {
                        content = it
                        viewModel.onContentChange(it)
                    }
                )
            }
        }
    )
}