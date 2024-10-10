package app.presentation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.presentation.compose.Screen
import app.presentation.note.addnote.AddNoteScreen
import app.presentation.alarm.alarmclock.AddAlarmScreen
import app.presentation.alarm.alarmclock.AlarmClock
import app.presentation.note.home.HomeScreen
import app.presentation.compose.todo.ToDoScreen
import app.presentation.splash.SplashScreen
import app.presentation.note.notecontent.NoteContent
import app.presentation.alarm.viewmodels.AlarmViewModel
import app.presentation.note.viewmodels.NoteViewModel

@Composable
fun AppNavHost (
    navController: NavHostController,
    drawerState: DrawerState,
    noteViewModel: NoteViewModel = hiltViewModel(),
    alarmViewModel: AlarmViewModel = hiltViewModel()
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