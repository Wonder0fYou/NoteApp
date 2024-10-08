package app.presentation.compose.alarmclock

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import app.domain.entity.AlarmClockItem
import app.presentation.compose.alarmclock.components.AddAlarmTopBar
import app.presentation.compose.alarmclock.timeinput.ClockInput
import app.presentation.viewmodels.AlarmViewModel

@Composable
fun AddAlarmScreen(
    onSaveClick: () -> Unit,
    viewModel: AlarmViewModel,
    onBackClick: () -> Unit
) {
    var alarmClockItem = AlarmClockItem(
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
                alarmClockItem = alarmClockItem
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