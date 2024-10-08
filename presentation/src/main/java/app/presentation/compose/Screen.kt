package app.presentation.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen (
    val title: String? = null,
    val route: String,
    val icon: ImageVector? = null,
    val navArgument: List<NamedNavArgument> = emptyList()
) {
    //splash screen
    data object Splash: Screen(route = "splashScreen")
    //note screens
    data object Home: Screen(
        title = "Notes",
        route = "homeScreen",
        icon = Icons.Default.MailOutline
    )
    data object AddNote: Screen(route = "addNoteScreen")
    data object NoteContent: Screen(
        route = "noteContent/{noteId}",
        navArgument = listOf(navArgument("noteId"){
            type = NavType.StringType
        })
    ) {
        fun createRoute(noteId: String) = "noteContent/${noteId}"
    }
    //alarm screens
    data object AlarmClock: Screen(
        title = "Alarm Clock",
        route = "alarmClock",
        icon = Icons.Default.Phone
    )
    data object AddAlarmClock: Screen(
        route = "addAlarmClockScreen"
    )
    //To Do screens
    data object ToDo: Screen(
        title = "To-Do List",
        route = "todoList",
        icon = Icons.AutoMirrored.Filled.List
    )
}