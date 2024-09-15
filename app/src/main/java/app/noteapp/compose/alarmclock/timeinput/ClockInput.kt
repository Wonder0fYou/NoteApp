package app.noteapp.compose.alarmclock.timeinput

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ClockInput(
    paddingValues: PaddingValues = PaddingValues(16.dp)
) {
    val openBottomSheetDescription = rememberSaveable {
        mutableStateOf(false)
    }

    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .imePadding(),
    ) {
        Surface (
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .focusRequester(FocusRequester.Cancel)
        ) {
            TimeInput(
                state = timePickerState,
                colors = TimePickerDefaults.colors(
                    timeSelectorUnselectedContainerColor = Color.White,
                    timeSelectorSelectedContainerColor = Color.White
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
        Melody()
        Replay()
        Vibration()
        DeleteAfterUse()
        Description(
            onDescriptionClick = {
                openBottomSheetDescription.value = true
            },
            openBottomSheetDescription = openBottomSheetDescription
        )
    }
}

@Composable
private fun DeleteAfterUse() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RectangleShape,
        modifier = Modifier
            .clickable { }
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                text = "Удалить после срабатывания",
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = false,
                onCheckedChange = {}
            )
        }
    }
}

@Composable
private fun Vibration() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RectangleShape,
        modifier = Modifier
            .clickable { }
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text(
                text = "Вибрировать при сигнале",
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = true,
                onCheckedChange = {}
            )
        }
    }
}

@Composable
private fun Replay() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RectangleShape,
        modifier = Modifier
            .clickable { }
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                text = "Повтор",
                fontSize = 22.sp,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Однократно",
                modifier = Modifier

            )
        }
    }
}

@Composable
private fun Melody() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RectangleShape,
        modifier = Modifier
            .clickable { }
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                text = "Мелодия",
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Мелодия по умолчанию")
        }
    }
}