package app.presentation.note.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    drawerState: DrawerState
) {
    val scope = rememberCoroutineScope()
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {drawerState.open()}
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        title = { Text("Заметки", fontSize = 30.sp) },
        actions = {
            IconButton(onClick = {

            }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
        }
    )
}