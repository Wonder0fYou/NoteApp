package app.noteapp.compose.alarmclock

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import app.noteapp.compose.alarmclock.components.AlarmFloatingActionButton
import app.noteapp.compose.alarmclock.components.TopAlarm
import app.noteapp.viewmodels.AlarmViewModel

@Composable
fun AlarmClock (
    drawerState: DrawerState,
    onAddAlarm: () -> Unit,
    alarmViewModel: AlarmViewModel = hiltViewModel()
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
        Box (
            modifier = Modifier.padding(paddingValues)
        ){
            
        }
    }
}