package app.presentation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val navArgument: List<NamedNavArgument> = emptyList()
) {
    //note screens
    data object Home: Screen(
        route = "homeScreen",
    )
    data object AddNote: Screen(
        route = "addNoteScreen"
    )
    data object NoteContent: Screen(
        route = "noteContent/{noteId}",
        navArgument = listOf(navArgument("noteId"){
            type = NavType.StringType
        })
    ) {
        fun createRoute(noteId: String) = "noteContent/${noteId}"
    }
}