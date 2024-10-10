package app.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import app.presentation.NoteApp
import app.presentation.theme.NoteAppTheme
import app.presentation.alarm.viewmodels.AlarmViewModel
import app.presentation.note.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Displaying edge-to-edge
        enableEdgeToEdge()
        setContent {
            NoteAppTheme {
                NoteApp(
                )
            }
        }
    }
}