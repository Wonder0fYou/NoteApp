package app.presentation.compose.alarmclock

import android.util.Log
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.domain.entity.AlarmClockItem
import app.presentation.compose.alarmclock.components.AlarmContent
import app.presentation.compose.alarmclock.components.AlarmFloatingActionButton
import app.presentation.compose.alarmclock.components.TopAlarm
import app.presentation.viewmodels.AlarmViewModel

@Composable
fun AlarmClock (
    drawerState: DrawerState,
    onAddAlarm: () -> Unit,
    alarmViewModel: AlarmViewModel,
    onAlarmClick: (AlarmClockItem) -> Unit = {}
) {
    val alarmState by alarmViewModel.alarms.collectAsState()
    Log.d("AlarmClock","${alarmState.size}")
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
            onAlarmClockClick = onAlarmClick
        )
    }
}