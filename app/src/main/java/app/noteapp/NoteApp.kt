package app.noteapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.noteapp.compose.Screen
import app.noteapp.viewmodels.NoteViewModel
import kotlinx.coroutines.launch

@Composable
fun NoteApp () {
    val uiViewModel = hiltViewModel<NoteViewModel>()
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: Screen.Home
    val scope = rememberCoroutineScope()

    val screens = listOf(
        Screen.Home,
        Screen.AlarmClock,
        Screen.ToDo
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                screens.forEach { screen ->
                    NavigationDrawerItem(
                        label = { screen.title?.let { Text(text = it) } },
                        icon = {
                            screen.icon?.let { Icon(imageVector = it, contentDescription = "${screen.title} icon") }
                        },
                        selected = currentRoute == screen.route,
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding),
                        onClick = {
                            navController.navigate(screen.route) {
                                launchSingleTop = true
                            }
                            scope.launch { drawerState.close() }
                        }
                    )
                }
            }
        }) {
        Scaffold {
            Surface (
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
            ) {
                NoteNavHost(viewModel = uiViewModel, navController = navController, drawerState = drawerState)
            }
        }
    }
}