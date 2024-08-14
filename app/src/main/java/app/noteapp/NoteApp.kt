package app.noteapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.noteapp.compose.notecontent.NoteContent
import compose.Screen
import app.noteapp.compose.addnote.AddNoteScreen
import app.noteapp.compose.home.HomeScreen

@Composable
fun NoteApp () {
    val navController = rememberNavController()
    NoteNavHost(
        navController = navController
    )
}

@Composable
fun NoteNavHost (
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {

        //HomeScreen
        composable(route = Screen.Home.route) {
            HomeScreen(
                onNoteClick = {
                    navController.navigate(
                        Screen.NoteContent.createRoute(
                            noteId = it.noteId.toString()
                        )
                    )
                },
                onAddNoteClick = {
                    navController.navigate(
                        Screen.AddNote.route
                    )
                }
            )
        }

        //NoteContentScreen
        composable(
            route = Screen.NoteContent.route,
            arguments = Screen.NoteContent.navArgument
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")?.toIntOrNull() ?: -1
            NoteContent (
                onBackClick = {
                    navController.navigateUp()
                },
                noteId = noteId
            )
        }
        //AddNoteScreen
        composable(
            route = Screen.AddNote.route
        ) {
            AddNoteScreen (
                onSaveClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}