package ru.mys_ya.ssdiary.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    background = Color.White,
    primary = Blue700,
    primaryVariant = Blue100,
    secondary = Orange100,
    onSecondary = Color.Black,
    surface = Blue50,
    onSurface = Color.Black
)

@Composable
fun SSDiaryTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        LightColorPalette
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