package app.presentation.alarm.alarmclock.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun AlarmFloatingActionButton(
    onAddAlarm: () -> Unit
) {
    FloatingActionButton(
        onClick = {onAddAlarm()},
        shape = CircleShape,
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Alarm add"
        )
    }
}