package app.presentation.alarm.alarmclock.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.domain.entity.AlarmClockItem
import app.domain.entity.DayOfWeek
import app.presentation.R
import app.presentation.alarm.viewmodels.AlarmViewModel

@Composable
fun AlarmContent(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    alarms: List<AlarmClockItem>,
    onAlarmClockClick: (AlarmClockItem) -> Unit,
    viewModel: AlarmViewModel = hiltViewModel()
) {
    Column (
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
        ){
            items(alarms) { alarm ->
                val switchIsEnabled = rememberSaveable {
                    mutableStateOf(alarm.isEnabled)
                }
                Card(
                    onClick = { onAlarmClockClick(alarm)},
                    modifier = Modifier
                        .height(120.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    shape = RectangleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            val hours = alarm.minutesFromStartOfDay / 60
                            val minutes = alarm.minutesFromStartOfDay % 60
                            val formattedTime = String.format("%02d:%02d", hours, minutes)
                            Text(
                                text = formattedTime,
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    color = MaterialTheme.colorScheme.onSurface
                                ),
                                modifier = Modifier.weight(1f),
                            )
                            Switch(
                                checked = switchIsEnabled.value,
                                onCheckedChange = {
                                    switchIsEnabled.value = it
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                                    uncheckedThumbColor = MaterialTheme.colorScheme.onSurface
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            DayOfWeek.entries.forEach { day ->
                                val isActive = day in alarm.dayOfTheWeek
                                val textColor = if (isActive) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clickable {
                                            //add update with viewmodel
                                            alarm.dayOfTheWeek = if (isActive) {
                                                alarm.dayOfTheWeek.minus(day)
                                            } else {
                                                alarm.dayOfTheWeek.plus(day)
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = stringResource(
                                            id = when (day) {
                                                DayOfWeek.MONDAY -> R.string.mon
                                                DayOfWeek.TUESDAY -> R.string.tue
                                                DayOfWeek.WEDNESDAY -> R.string.wed
                                                DayOfWeek.THURSDAY -> R.string.thu
                                                DayOfWeek.FRIDAY -> R.string.fri
                                                DayOfWeek.SATURDAY -> R.string.sat
                                                DayOfWeek.SUNDAY -> R.string.sun
                                                else -> R.string.mon
                                            }
                                        ),
                                        fontSize = 12.sp,
                                        color = textColor,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}