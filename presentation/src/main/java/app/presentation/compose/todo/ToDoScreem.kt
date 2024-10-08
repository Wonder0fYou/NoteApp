package app.presentation.compose.todo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.presentation.compose.todo.components.TopToDo

@Composable
fun ToDoScreen (
    drawerState: DrawerState
) {
    Scaffold (
        topBar = {
            TopToDo(drawerState = drawerState)
        }
    ) { paddingValues ->
        Box (
            modifier = Modifier.padding(paddingValues)
        ){

        }
    }
}