package app.presentation.compose.alarmclock.timeinput

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Melody (
    onMelodyClick: () -> Unit,
    openBottomSheetMelody: MutableState<Boolean>
) {
    val sheetState = rememberModalBottomSheetState()
    val musicList = listOf(
        "Song 1",
        "Song 2",
        "Song 3"
    )

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RectangleShape,
        modifier = Modifier
            .clickable {
                onMelodyClick()
            }
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.melody),
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = stringResource(id = R.string.default_melody))
        }
    }
    if (openBottomSheetMelody.value) {
        ModalBottomSheet(
            onDismissRequest = { openBottomSheetMelody.value = false},
            sheetState = sheetState,
            content = {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ){
                    Text(
                        text = stringResource(id = R.string.music_list),
                        fontSize = 25.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                    LazyColumn {
                        items(musicList) { song ->
                            Text(
                                text = song,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                        }
                    }
                }
            }
        )
    }
}