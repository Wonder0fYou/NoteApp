package app.presentation.alarm.alarmclock.timeinput

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.presentation.alarm.viewmodels.AlarmViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClockInput(
    paddingValues: PaddingValues = PaddingValues(16.dp),
    focusRequester: MutableState<FocusRequester>,
    viewModel: AlarmViewModel
) {
    val openDialogDescription = rememberSaveable {
        mutableStateOf(false)
    }
    val openBottomSheetMelody = rememberSaveable {
        mutableStateOf(false)
    }
    val switchDeleteAfterUse = rememberSaveable {
        mutableStateOf(false)
    }
    val switchVibration = rememberSaveable {
        mutableStateOf(false)
    }
    val openBottomSheetReplay = rememberSaveable {
        mutableStateOf(false)
    }

    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )
    Column (
        modifier = Modifier
            .padding(paddingValues)
            .imePadding()
            .fillMaxWidth(),
    ) {
        Surface (
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .focusRequester(focusRequester.value),
            color = MaterialTheme.colorScheme.surface
        ) {
            TimeInput(
                state = timePickerState,
                colors = TimePickerDefaults.colors(
                    timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    timeSelectorSelectedContainerColor = MaterialTheme.colorScheme.primary,
                    timeSelectorSelectedContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .focusRequester(focusRequester.value)
            )
        }
        val selectedHour = timePickerState.hour
        val selectedMinute = timePickerState.minute
        viewModel.addTime(selectedHour, selectedMinute)

        //melody
        Melody(
            onMelodyClick = {
                openBottomSheetMelody.value = true
            },
            openBottomSheetMelody = openBottomSheetMelody,
            viewModel = viewModel
        )

        //replay (private fun input listOfWeek)
        val selectedDays by viewModel.listOfWeek.collectAsStateWithLifecycle()
        Replay(
            onReplayClick = {
                openBottomSheetReplay.value = true
            },
            openBottomSheetReplay = openBottomSheetReplay,
            viewModel = viewModel,
            selectedDays = selectedDays
        )

        //vibration
        Vibration(
            onVibrateClick = {
                switchVibration.value = !switchVibration.value
            },
            switchVibration = switchVibration,
            viewModel = viewModel
        )

        //delete after use
        DeleteAfterUse(
            onDeleteAfterUseClick = {
                switchDeleteAfterUse.value = !switchDeleteAfterUse.value
            },
            switchDeleteAfterUse = switchDeleteAfterUse,
            viewModel = viewModel
        )

        //description
        var description by remember {
            mutableStateOf("")
        }
        Description(
            onDescriptionClick = {
                openDialogDescription.value = true
            },
            openDialogDescription = openDialogDescription,
            description = description,
            onDescriptionChange = {
                description = it
            },
            viewModel = viewModel
        )
    }
}