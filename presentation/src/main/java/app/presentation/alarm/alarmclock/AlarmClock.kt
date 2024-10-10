package app.presentation.alarm.alarmclock

import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.domain.entity.AlarmClockItem
import app.presentation.alarm.alarmclock.components.AlarmContent
import app.presentation.alarm.alarmclock.components.AlarmFloatingActionButton
import app.presentation.alarm.alarmclock.components.TopAlarm
import app.presentation.alarm.viewmodels.AlarmViewModel

@Composable
fun AlarmClock (
    drawerState: DrawerState,
    onAddAlarm: () -> Unit,
    alarmViewModel: AlarmViewModel,
    onAlarmClick: (AlarmClockItem) -> Unit = {}
) {
    val alarmState by alarmViewModel.alarms.collectAsState()
    Scaffold (
        topBar = {
            TopAlarm(drawerState = drawerState)
        },
        floatingActionButton = {
            AlarmFloatingActionButton (
                onAddAlarm = onAddAlarm
            )
        }
    ) { paddingValues ->  
        AlarmContent(
            paddingValues = paddingValues,
            alarms = alarmState,
            onAlarmClockClick = onAlarmClick,
            viewModel = alarmViewModel
        )
    }
}