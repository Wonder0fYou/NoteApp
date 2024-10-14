package com.example.cftapp.presentation.note.addnote.components

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable

@Composable
fun AddSnackBar(
    snackBarHostState: SnackbarHostState
) {
    SnackbarHost(
        hostState = snackBarHostState,
        snackbar = { snackbarData ->
            Snackbar(
                snackbarData = snackbarData
            )
        }
    )
}