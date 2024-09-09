package app.noteapp

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.noteapp.compose.Screen
import app.noteapp.compose.addnote.AddNoteScreen
import app.noteapp.compose.alarmclock.AddAlarmScreen
import app.noteapp.compose.alarmclock.AlarmClock
import app.noteapp.compose.home.HomeScreen
import app.noteapp.compose.notecontent.NoteContent
import app.noteapp.compose.todo.ToDoScreen
import app.noteapp.splash.SplashScreen
import app.noteapp.viewmodels.AlarmViewModel
import app.noteapp.viewmodels.NoteViewModel

@Composable
fun AppNavHost (
    noteViewModel: NoteViewModel,
    navController: NavHostController,
    drawerState: DrawerState,
    alarmViewModel: AlarmViewModel
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

        //AlarmClock
        composable(
            route = Screen.AlarmClock.route
        ) {
            AlarmClock(
                drawerState = drawerState,
                alarmViewModel = alarmViewModel,
                onAddAlarm = {
                    navController.navigate(
                        Screen.AddAlarmClock.route
                    )
                }
            )
        }

        //AddAlarm
        composable(
            route = Screen.AddAlarmClock.route
        ) {
            AddAlarmScreen(
                onSaveClick = {
                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                },
                viewModel = alarmViewModel
            )
        }

        //TodoScreen
        composable(
            route = Screen.ToDo.route
        ) {
            ToDoScreen(
                drawerState = drawerState
            )
        }
    }
}