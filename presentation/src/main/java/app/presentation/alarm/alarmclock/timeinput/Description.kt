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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import app.presentation.R
import app.presentation.alarm.viewmodels.AlarmViewModel

@OptIn(ExperimentalMaterial3Api::class)
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
            containerColor = MaterialTheme.colorScheme.surface
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
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Description",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
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
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .imePadding()
                ) {
                    Text(
                        text = stringResource(id = R.string.add_description_alarm),
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = description,
                        onValueChange = onDescriptionChange,
                        label = { Text(text = stringResource(id = R.string.text_description)) },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            focusedLabelColor = MaterialTheme.colorScheme.primary
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
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(text = stringResource(id = R.string.cancel))
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            onClick = { viewModel.addDescription(description) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
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