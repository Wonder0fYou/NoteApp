package com.example.cftapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import app.presentation.NoteApp
import app.presentation.note.NoteViewModel
import app.presentation.theme.CFTappTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val appComponent = (application as CFTApplication).appComponent
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CFTappTheme {
                NoteApp(
                    noteViewModel = noteViewModel
                )
            }
        }
    }
}