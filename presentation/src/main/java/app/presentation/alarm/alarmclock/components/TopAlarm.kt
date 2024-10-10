package app.presentation.alarm.alarmclock.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import app.domain.entity.AlarmClockItem
import app.presentation.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAlarm (
    drawerState: DrawerState,
    alarms: List<AlarmClockItem>
) {
    val scope = rememberCoroutineScope()
    val topName: String = stringResource(id = if (alarms.size == 1) R.string.alarm else R.string.alarms)
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                scope.launch { drawerState.open() }}
            ) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        title = {
            Text(
                text = topName,
                fontSize = 30.sp
            )
        }
    )
}