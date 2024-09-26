package app.noteapp.compose.alarmclock.timeinput

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.noteapp.R
import app.noteapp.domain.model.DayOfWeek
import app.noteapp.viewmodels.AlarmViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Replay (
    onReplayClick: () -> Unit,
    openBottomSheetReplay: MutableState<Boolean>,
    viewModel: AlarmViewModel = hiltViewModel(),
    selectedDays: Set<DayOfWeek>,
    onChangeSelectedDays: (Set<DayOfWeek>) -> Unit
) {

    val listOfWeek = listOf(
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday"
    )

    val sheetState = rememberModalBottomSheetState()

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RectangleShape,
        modifier = Modifier
            .clickable {
                onReplayClick()
            }
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.replay),
                fontSize = 22.sp,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Choose music"
            )
        }
    }
    if (openBottomSheetReplay.value) {
        ModalBottomSheet(
            onDismissRequest = { openBottomSheetReplay.value = false },
            sheetState = sheetState,
            content = {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    LazyColumn (
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        items(listOfWeek) { day->
                            var selectedDay by remember {
                                mutableStateOf(selectedDays.contains(when (day) {
                                    "Monday" -> DayOfWeek.MONDAY
                                    "Tuesday" -> DayOfWeek.TUESDAY
                                    "Wednesday" -> DayOfWeek.WEDNESDAY
                                    "Thursday" -> DayOfWeek.THURSDAY
                                    "Friday" -> DayOfWeek.FRIDAY
                                    "Saturday" -> DayOfWeek.SATURDAY
                                    "Sunday" -> DayOfWeek.SUNDAY
                                    else -> DayOfWeek.MONDAY
                                }))
                            }
                            Card (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedDay = !selectedDay
                                    }
                            ) {
                                Row {
                                    Text(
                                        text = stringResource(
                                            id = when (day) {
                                                "Monday" -> R.string.monday
                                                "Tuesday" -> R.string.tuesday
                                                "Wednesday" -> R.string.wednesday
                                                "Thursday" -> R.string.thursday
                                                "Friday" -> R.string.friday
                                                "Saturday" -> R.string.saturday
                                                "Sunday" -> R.string.sunday
                                                else -> R.string.monday
                                            }
                                        ),
                                        fontSize = 20.sp,
                                        modifier = Modifier
                                            .padding(10.dp)
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Checkbox(
                                        checked = selectedDay,
                                        onCheckedChange = {
                                            selectedDay = it
                                            if (it) {
                                                when (day) {
                                                    "Monday" -> selectedDays.plus(DayOfWeek.MONDAY)
                                                    "Tuesday" -> selectedDays.plus(DayOfWeek.TUESDAY)
                                                    "Wednesday" -> selectedDays.plus(DayOfWeek.WEDNESDAY)
                                                    "Thursday" -> selectedDays.plus(DayOfWeek.THURSDAY)
                                                    "Friday" -> selectedDays.plus(DayOfWeek.FRIDAY)
                                                    "Saturday" -> selectedDays.plus(DayOfWeek.SATURDAY)
                                                    "Sunday" -> selectedDays.plus(DayOfWeek.SUNDAY)
                                                }
                                            } else {
                                                when (day) {
                                                    "Monday" -> selectedDays.minus(DayOfWeek.MONDAY)
                                                    "Tuesday" -> selectedDays.minus(DayOfWeek.TUESDAY)
                                                    "Wednesday" -> selectedDays.minus(DayOfWeek.WEDNESDAY)
                                                    "Thursday" -> selectedDays.minus(DayOfWeek.THURSDAY)
                                                    "Friday" -> selectedDays.minus(DayOfWeek.FRIDAY)
                                                    "Saturday" -> selectedDays.minus(DayOfWeek.SATURDAY)
                                                    "Sunday" -> selectedDays.minus(DayOfWeek.SUNDAY)
                                                }
                                            }
                                            onChangeSelectedDays(selectedDays)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}