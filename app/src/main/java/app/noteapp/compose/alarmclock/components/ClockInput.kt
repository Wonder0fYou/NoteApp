package app.noteapp.compose.alarmclock.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusOrder
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ClockInput(
    paddingValues: PaddingValues = PaddingValues(16.dp)
) {
    val scope = rememberCoroutineScope()
    var openBottomSheetDescription by rememberSaveable {
        mutableStateOf(false)
    }
    var skipPartiallyExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    val bottomSheetStateDescription = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)
    var textDescription by remember {
        mutableStateOf("")
    }
    val focusRequester = remember {
        FocusRequester()
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
                openBottomSheetDescription = true
            }
        )
        if (openBottomSheetDescription) {
            ModalBottomSheet(
                onDismissRequest = { openBottomSheetDescription = false },
                sheetState = bottomSheetStateDescription,
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .imePadding()
                    ) {
                        Text(
                            text = "Добавить описание будильника",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(
                            value = textDescription,
                            onValueChange = {textDescription = it},
                            label = { Text(text = "Текст описания")},
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White
                            ),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .focusRequester(focusRequester)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row {
                            Button(
                                onClick = {
                                    scope
                                        .launch { bottomSheetStateDescription.hide() }
                                        .invokeOnCompletion {
                                            if (!bottomSheetStateDescription.isVisible) {
                                                openBottomSheetDescription = false
                                            }
                                        }
                                }
                            ) {
                                Text(text = "Отмена")
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Button(
                                onClick = { /*TODO*/ }
                            ) {
                                Text(text = "ОК")
                            }
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun Description(onDescriptionClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RectangleShape,
        modifier = Modifier
            .clickable {
                onDescriptionClick()
            }
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                text = "Описание",
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Description"
            )
        }
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