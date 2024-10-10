package app.presentation.alarm.alarmclock.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Calendar

@Composable
fun ClockPicker (
    paddingValues: PaddingValues = PaddingValues(16.dp),
    selectedTime: Calendar,
    onValueChange: (Calendar) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        LazyColumn (
            modifier = Modifier
                .weight(1f)
                .height(300.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            items(24) { hour ->
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                val formatedHour = SimpleDateFormat("HH").format(calendar.time)
                Text(
                    text = formatedHour,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(60.dp)
                        .offset(x = 70.dp)
                        .clickable {
                            onValueChange(calendar.apply {
                                set(
                                    Calendar.MINUTE,
                                    selectedTime.get(Calendar.MINUTE)
                                )
                            })
                        },
                    fontWeight = FontWeight.Medium,
                    fontSize = 60.sp
                )
            }
        }
        VerticalDivider(
            modifier = Modifier
                .height(220.dp)
                .offset(y = 40.dp),
            color = Color.LightGray,
            thickness = DividerDefaults.Thickness
        )
        LazyColumn (
            modifier = Modifier
                .weight(1f)
                .height(300.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            items(24) { minute ->
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.MINUTE, minute)
                val formatedMinute = SimpleDateFormat("MM").format(calendar.time)
                Text(
                    text = formatedMinute,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(60.dp)
                        .offset(x = 60.dp)
                        .clickable {
                            onValueChange(calendar.apply {
                                set(
                                    Calendar.MINUTE,
                                    selectedTime.get(Calendar.MINUTE)
                                )
                            })
                        },
                    fontWeight = FontWeight.Medium,
                    fontSize = 60.sp
                )
            }
        }
    }
}