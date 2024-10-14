package com.example.cftapp.presentation.note.addnote

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.cftapp.domain.entity.NoteItem
import com.example.cftapp.presentation.note.addnote.components.AddContent
import com.example.cftapp.presentation.note.addnote.components.AddSnackBar
import com.example.cftapp.presentation.note.addnote.components.AddTopBar
import com.example.cftapp.presentation.viewmodels.NoteViewModel
import java.util.Date

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