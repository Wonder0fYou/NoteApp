package app.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
    val notesState by noteViewModel.noteState.collectAsState()
//    val viewModel: NoteViewModel = daggerViewModel {
//        Dagger2NoteComponent.factory().create().getViewModel()
//    }
    NavHost(navController = navController, startDestination = Screen.Home.route) {

        //HomeScreen
        composable(route = Screen.Home.route) {
            HomeScreen(
                notesState = notesState,
                onAction = noteViewModel::onAction,
                onNoteClick = {
                    navController.navigate(
                        Screen.NoteContent.createRoute(
                            noteId = it.noteId.toString()
                        )
                    )
                    noteViewModel.getNote(it.noteId)
                },
                onAddNoteClick = {
                    navController.navigate(
                        Screen.AddNote.route
                    )
                },
            )
        }

        //NoteContentScreen
        composable(
            route = Screen.NoteContent.route,
            arguments = Screen.NoteContent.navArgument
        ) {
            NoteContentScreen (
                onAction = noteViewModel::onAction,
                notesState = notesState,
                onBackClick = {
                    navController.navigateUp()
                },
                onDeleteClick = {
                    navController.popBackStack()
                }
            )
        }

        //AddNoteScreen
        composable(
            route = Screen.AddNote.route
        ) {
            AddNoteScreen (
                onAction = noteViewModel::onAction,
                onSaveClick = {
                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                },
                notesState = notesState
            )
        }
    }
}