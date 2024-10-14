package app.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.presentation.note.addNote.AddNoteScreen
import app.presentation.note.home.HomeScreen
import app.presentation.note.NoteViewModel
import app.presentation.note.noteContent.NoteContentScreen

@Composable
fun NoteApp(
    noteViewModel: NoteViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {

        //HomeScreen
        composable(route = Screen.Home.route) {
            HomeScreen(
                viewModel = noteViewModel,
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
            NoteContentScreen (
                viewModel = noteViewModel,
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
                viewModel = noteViewModel,
                onSaveClick = {
                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}