package app.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = DarkRed,
    onPrimary = Silver,
    primaryContainer = Charcoal,
    onPrimaryContainer = Crimson,
    secondary = Crimson,
    onSecondary = Charcoal,
    background = Charcoal,
    onBackground = Silver,
    surface = Charcoal,
    onSurface = Silver
)

private val LightColorScheme = lightColorScheme(
    primary = LightCoral,
    onPrimary = White,
    primaryContainer = White,
    onPrimaryContainer = DarkGray,
    secondary = LightRed,
    onSecondary = White,
    background = White,
    onBackground = DarkGray,
    surface = LightGray,
    onSurface = DarkGray
)

@Composable
fun CFTappTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}