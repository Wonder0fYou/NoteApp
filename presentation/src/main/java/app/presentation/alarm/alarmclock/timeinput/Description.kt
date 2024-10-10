package app.presentation.alarm.alarmclock.timeinput

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import app.presentation.R
import app.presentation.alarm.viewmodels.AlarmViewModel

@Composable
fun Description (
    onDescriptionClick: () -> Unit,
    openDialogDescription: MutableState<Boolean>,
    description: String,
    onDescriptionChange: (String) -> Unit,
    viewModel: AlarmViewModel
) {
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
                text = stringResource(id = R.string.description),
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Description"
            )
        }
    }
    if (openDialogDescription.value) {
        Dialog(
            onDismissRequest = { openDialogDescription.value = false},
            content = {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .imePadding()
                ) {
                    Text(
                        text = stringResource(id = R.string.add_description_alarm),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = description,
                        onValueChange = onDescriptionChange,
                        label = { Text(text = stringResource(id = R.string.text_description)) },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .focusRequester(focusRequester)
                    )
                    Row {
                        Button(
                            onClick = {
                                openDialogDescription.value = false
                                textDescription = ""
                            }
                        ) {
                            Text(text = stringResource(id = R.string.cancel))
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            onClick = { viewModel.addDescription(description) }
                        ) {
                            Text(text = stringResource(id = R.string.ok))
                        }
                    }
                }
                    }
            }
        )
    }
}