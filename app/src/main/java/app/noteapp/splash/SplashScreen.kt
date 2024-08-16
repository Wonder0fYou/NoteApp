package app.noteapp.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import app.noteapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen (
    navigateToHomeScreen: () -> Unit
) {
    Box (
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.splash_image),
            contentDescription = "Splash Screen Image"
        )
    }

    LaunchedEffect(Unit) {
        delay(3000)
        navigateToHomeScreen()
    }
}