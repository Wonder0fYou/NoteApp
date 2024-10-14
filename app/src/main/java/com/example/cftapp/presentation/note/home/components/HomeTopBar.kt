package com.example.cftapp.presentation.note.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.cftapp.R
import com.example.cftapp.domain.entity.NoteItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    notesList: List<NoteItem>
) {
    val topText: String = stringResource(id = if (notesList.size == 1) R.string.note else R.string.notes)
    TopAppBar(
        title = {
            Text(
                text = topText,
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        actions = {
            IconButton(onClick = {
                //not implemented
            }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}