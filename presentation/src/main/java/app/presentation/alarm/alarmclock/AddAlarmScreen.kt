package app.presentation.alarm.alarmclock

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import app.domain.entity.AlarmClockItem
import app.presentation.alarm.alarmclock.components.AddAlarmTopBar
import app.presentation.alarm.alarmclock.timeinput.ClockInput
import app.presentation.alarm.viewmodels.AlarmViewModel

@Composable
fun AddAlarmScreen(
    onSaveClick: () -> Unit,
    viewModel: AlarmViewModel,
    onBackClick: () -> Unit
) {
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
            viewModel = viewModel
        )
    }
}