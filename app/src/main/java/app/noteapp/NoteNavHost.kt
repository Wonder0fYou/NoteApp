package app.noteapp

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.noteapp.compose.Screen
import app.noteapp.compose.addnote.AddNoteScreen
import app.noteapp.compose.home.HomeScreen
import app.noteapp.compose.notecontent.NoteContent
import app.noteapp.splash.SplashScreen
import app.noteapp.viewmodels.NoteViewModel

@Composable
fun NoteNavHost (
    viewModel: NoteViewModel,
    navController: NavHostController,
    drawerState: DrawerState
) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        //SplashScreen
        composable(route = Screen.Splash.route) {
            SplashScreen(
                navigateToHomeScreen = {
                    navController.navigate(
                        Screen.Home.route
                    )
                }
            )
        }

        //HomeScreen
        composable(route = Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
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
                },
                drawerState = drawerState
            )
        }

        //NoteContentScreen
        composable(
            route = Screen.NoteContent.route,
            arguments = Screen.NoteContent.navArgument
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")?.toIntOrNull() ?: -1
            NoteContent (
                viewModel = viewModel,
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
                viewModel = viewModel,
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