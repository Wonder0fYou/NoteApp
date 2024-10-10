package app.presentation.alarm.alarmclock.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import app.domain.entity.AlarmClockItem
import app.presentation.alarm.viewmodels.AlarmViewModel

@Composable
fun AlarmContent(
    paddingValues: PaddingValues,
    alarms: List<AlarmClockItem>,
    onAlarmClockClick: (AlarmClockItem) -> Unit,
    viewModel: AlarmViewModel
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
                    mutableStateOf(false)
                }
                Card(
                    onClick = { onAlarmClockClick(alarm)},
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RectangleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Text(text = alarm.minutesFromStartOfDay.toString())
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Card {
                            alarm.dayOfTheWeek
                        }
                    }
                    HorizontalDivider(
                        color = Color.LightGray,
                        thickness = 1.dp
                    )
                }
            }
        }
    }
}