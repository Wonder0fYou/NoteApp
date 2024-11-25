package com.example.cftapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.domain.repository.NoteRepository
import app.presentation.NoteApp
import app.presentation.note.NoteViewModel
import app.presentation.theme.CFTappTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var noteRepository: NoteRepository

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val appComponent = (application as CFTApplication).appComponent
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        noteViewModel = ViewModelProvider(this, ViewModelFactory(noteRepository)).get(NoteViewModel::class.java)
        setContent {
            CFTappTheme {
                NoteApp(
                    noteViewModel = noteViewModel
                )
            }
        }
    }
}

class ViewModelFactory @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(noteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}