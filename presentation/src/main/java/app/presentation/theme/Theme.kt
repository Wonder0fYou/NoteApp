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
    primary = DeepIndigo,
    onPrimary = Silver,
    primaryContainer = CharcoalBlack,
    onPrimaryContainer = ElectricBlue,
    secondary = NeonPink,
    onSecondary = CharcoalBlack,
    background = CharcoalBlack,
    onBackground = Silver,
    surface = CharcoalBlack,
    onSurface = Silver
)

private val LightColorScheme = lightColorScheme(
    primary = Lavender,
    onPrimary = DarkPurple,
    primaryContainer = Cream,
    onPrimaryContainer = Mint,
    secondary = Amber,
    onSecondary = DarkPurple,
    background = Cream,
    onBackground = DarkPurple,
    surface = Cream,
    onSurface = DarkPurple
)

@Composable
fun NoteAppTheme(
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