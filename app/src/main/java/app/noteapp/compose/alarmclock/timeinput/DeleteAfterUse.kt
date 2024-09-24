package app.noteapp.compose.alarmclock.timeinput

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.noteapp.R

@Composable
fun DeleteAfterUse (
    onDeleteAfterUseClick: () -> Unit,
    switchDeleteAfterUse: MutableState<Boolean>
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RectangleShape,
        modifier = Modifier
            .clickable {
                onDeleteAfterUseClick()
            }
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.delete_after_use),
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = switchDeleteAfterUse.value,
                onCheckedChange = {
                    switchDeleteAfterUse.value = it
                }
            )
        }
    }
}