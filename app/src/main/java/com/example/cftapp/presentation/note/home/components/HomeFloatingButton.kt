package com.example.cftapp.presentation.note.home.components

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeFloatingButton(
    onAddNoteClick: () -> Unit
) {
    FloatingActionButton(
        onClick = { onAddNoteClick() },
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier
            .size(60.dp)
            .offset(y = (-25).dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add Note",
            modifier = Modifier
                .size(40.dp)
        )
    }
}