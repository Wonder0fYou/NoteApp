package app.noteapp.compose.alarmclock.timeinput

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.noteapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Replay (
    onReplayClick: () -> Unit,
    openBottomSheetReplay: MutableState<Boolean>
) {
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
            Text(
                text = "Однократно",
                modifier = Modifier
            )
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

            }
        )
    }
}