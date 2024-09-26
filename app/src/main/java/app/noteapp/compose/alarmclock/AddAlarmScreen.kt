package app.noteapp.compose.alarmclock

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.hilt.navigation.compose.hiltViewModel
import app.noteapp.compose.alarmclock.components.AddAlarmTopBar
import app.noteapp.compose.alarmclock.model.AlarmClockItem
import app.noteapp.compose.alarmclock.timeinput.ClockInput
import app.noteapp.viewmodels.AlarmViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddAlarmScreen(
    onSaveClick: () -> Unit,
    viewModel: AlarmViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    var alarmClockItem = AlarmClockItem (
        description = "",
        isEnabled = true,
        time = 0,
        vibration = false,
        deleteAfterUse = false,
        song = "",
        dayOfTheWeek = emptySet()
    )
    val focusRequester = remember {
        mutableStateOf(FocusRequester())
    }
    Scaffold (
        topBar = {
            AddAlarmTopBar(
                onSaveClick = {onSaveClick()},
                viewModel = viewModel,
                onBackClick = {onBackClick()},
            )
        }
    ){ paddingValues ->
        ClockInput(
            paddingValues = paddingValues,
            focusRequester = focusRequester,
            alarmClockItem = {
                alarmClockItem = it
            },
            viewModel = viewModel
        )
    }
}