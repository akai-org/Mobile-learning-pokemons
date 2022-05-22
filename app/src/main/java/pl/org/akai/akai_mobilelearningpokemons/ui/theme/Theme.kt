package pl.org.akai.akai_mobilelearningpokemons.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Sand60,
    secondary = Sand100,
    error = Red80,
    onPrimary = DarkNavyBlue80,
    onSecondary = DarkNavyBlue80

)

private val LightColorPalette = lightColors(
    primary = Sand60,
    secondary = Sand100,
    error = Red80,
    onPrimary = DarkNavyBlue80,
    onSecondary = DarkNavyBlue80

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun AKAIMobileLearningPokemonsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}