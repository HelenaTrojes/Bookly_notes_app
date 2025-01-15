package dev.helena.bookly_notes_app.ui.theme

import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColors(
    primary = Color.White,
    background = DarkGray,
    onBackground = Color.White,
    surface = LightBlue,
    onSurface = DarkGray
)

@Composable
fun Bookly_notes_appTheme(
    darkTheme: Boolean = true,
    content: @Composable() () -> Unit) {

    MaterialTheme(
        shapes = shapes,
        typography = Typography,
        content = content,
        colors = DarkColorScheme
    )
}