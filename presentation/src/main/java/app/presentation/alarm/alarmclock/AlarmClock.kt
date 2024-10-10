package app.presentation.alarm.alarmclock

import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.domain.entity.AlarmClockItem
import app.presentation.alarm.alarmclock.components.AlarmContent
import app.presentation.alarm.alarmclock.components.AlarmFloatingActionButton
import app.presentation.alarm.alarmclock.components.TopAlarm
import app.presentation.alarm.viewmodels.AlarmViewModel

@Composable
fun AlarmClock (
    drawerState: DrawerState,
    onAddAlarm: () -> Unit,
    alarmViewModel: AlarmViewModel = hiltViewModel(),
    onAlarmClick: (AlarmClockItem) -> Unit = {}
) {
    val alarmsList by alarmViewModel.alarms.collectAsStateWithLifecycle()
    Scaffold (
        topBar = {
            TopAlarm(
                drawerState = drawerState,
                alarms = alarmsList
            )
        },
        floatingActionButton = {
            AlarmFloatingActionButton (
                onAddAlarm = onAddAlarm
            )
        }
    ) { paddingValues ->  
        AlarmContent(
            paddingValues = paddingValues,
            alarms = alarmsList,
            onAlarmClockClick = onAlarmClick,
            viewModel = alarmViewModel
        )
    }
}