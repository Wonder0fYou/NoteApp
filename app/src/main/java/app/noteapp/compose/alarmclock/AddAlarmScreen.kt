package app.noteapp.compose.alarmclock

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import app.noteapp.compose.alarmclock.components.AddAlarmTopBar
import app.noteapp.compose.alarmclock.timeinput.ClockInput
import app.noteapp.viewmodels.AlarmViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddAlarmScreen(
    onSaveClick: () -> Unit,
    viewModel: AlarmViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
//    var selectedTime = Calendar.getInstance()
    Scaffold (
        topBar = {
            AddAlarmTopBar(
                onSaveClick = {onSaveClick()},
                viewModel = viewModel,
                onBackClick = {onBackClick()}
            )
        }
    ){ paddingValues ->
//        ClockPicker(
//            paddingValues = paddingValues,
//            selectedTime = selectedTime,
//            onValueChange = { newTime ->
//                selectedTime = newTime
//            }
//        )
        ClockInput(
            paddingValues = paddingValues
        )
    }
}