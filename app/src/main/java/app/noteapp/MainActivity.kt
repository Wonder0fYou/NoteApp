package app.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import app.presentation.NoteApp
import app.presentation.theme.NoteAppTheme
import app.presentation.viewmodels.AlarmViewModel
import app.presentation.viewmodels.NoteViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var noteViewModel: NoteViewModel

    @Inject
    lateinit var alarmViewModel: AlarmViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (application as NoteApplication).appComponent
        appComponent.inject(this)
        // Displaying edge-to-edge
        enableEdgeToEdge()
        setContent {
            NoteAppTheme {
                NoteApp(
                    noteViewModel = noteViewModel,
                    alarmViewModel = alarmViewModel
                )
            }
        }
    }
}