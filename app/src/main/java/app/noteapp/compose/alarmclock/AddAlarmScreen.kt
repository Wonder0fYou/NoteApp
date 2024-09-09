package app.noteapp.compose.alarmclock

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import app.noteapp.viewmodels.AlarmViewModel

@Composable
fun AddAlarmScreen(
    onSaveClick: () -> Unit,
    viewModel: AlarmViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {

}