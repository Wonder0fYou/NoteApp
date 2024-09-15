package app.noteapp.compose.alarmclock.timeinput

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Description (
    onDescriptionClick: () -> Unit,
    openBottomSheetDescription: MutableState<Boolean>
) {
    val scope = rememberCoroutineScope()
    val skipPartiallyExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    val bottomSheetStateDescription = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)
    var textDescription by remember {
        mutableStateOf("")
    }
    val focusRequester = remember {
        FocusRequester()
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RectangleShape,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple()
            ) {
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
    if (openBottomSheetDescription.value) {
        ModalBottomSheet(
            onDismissRequest = { openBottomSheetDescription.value = false },
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
                                            openBottomSheetDescription.value = false
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