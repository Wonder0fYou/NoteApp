package app.presentation.alarm.alarmclock.timeinput

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.presentation.R
import app.presentation.alarm.viewmodels.AlarmViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Melody (
    onMelodyClick: () -> Unit,
    openBottomSheetMelody: MutableState<Boolean>,
    viewModel: AlarmViewModel
) {
    val sheetState = rememberModalBottomSheetState()
    val musicList = listOf(
        "Song 1",
        "Song 2",
        "Song 3"
    )

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
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
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.default_melody),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
    if (openBottomSheetMelody.value) {
        ModalBottomSheet(
            onDismissRequest = { openBottomSheetMelody.value = false},
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            content = {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ){
                    Text(
                        text = stringResource(id = R.string.music_list),
                        fontSize = 25.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                    )
                    LazyColumn {
                        items(musicList) { song ->
                            Text(
                                text = song,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier
                                    .padding(10.dp),
                            )
                        }
                    }
                }
                viewModel.addSong("")
            }
        )
    }
}