package app.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.presentation.alarm.viewmodels.AlarmViewModel
import app.presentation.compose.Screen
import app.presentation.note.viewmodels.NoteViewModel
import kotlinx.coroutines.launch

@Composable
fun NoteApp (
) {
    val noteViewModel: NoteViewModel = hiltViewModel<NoteViewModel>()
    val alarmViewModel: AlarmViewModel = hiltViewModel<AlarmViewModel>()
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
            ModalDrawerSheet (
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                screens.forEach { screen ->
                    NavigationDrawerItem(
                        label = {
                            screen.title?.let { Text(
                                text = it,
                                style = MaterialTheme.typography.titleMedium
                            )}},
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
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            unselectedContainerColor = MaterialTheme.colorScheme.background,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            unselectedTextColor = MaterialTheme.colorScheme.onBackground
                        )
                    )
                }
            }
        }) {
        Scaffold { paddingValues ->
            Surface (
                modifier = Modifier
                    .padding(top = paddingValues.calculateTopPadding()-20.dp),
                color = MaterialTheme.colorScheme.background
            ) {
                AppNavHost(
                    navController = navController,
                    drawerState = drawerState,
                    noteViewModel = noteViewModel,
                    alarmViewModel = alarmViewModel
                )
            }
        }
    }
}