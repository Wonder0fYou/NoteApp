package app.noteapp.compose.alarmclock

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.noteapp.compose.alarmclock.components.TopAlarm

@Composable
fun AlarmClock (
    drawerState: DrawerState
) {

    Scaffold (
        topBar = {
            TopAlarm(drawerState = drawerState)
        }
    ) { paddingValues ->  
        Box (
            modifier = Modifier.padding(paddingValues)
        ){
            
        }
    }
}