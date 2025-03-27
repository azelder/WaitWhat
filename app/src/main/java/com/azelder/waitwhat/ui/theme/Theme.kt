package com.azelder.waitwhat.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Viridian,
    onPrimary = Color.White,
    primaryContainer = ViridianLight,
    onPrimaryContainer = OnViridianDark,

    secondary = Peach,
    onSecondary = Color.Black,
    secondaryContainer = PeachLight,
    onSecondaryContainer = LightCoral,

    tertiary = Coral,
    onTertiary = Color.White,

    background = LightBackground,
    onBackground = LightOnBackground,
    surface = LightSurface,
    onSurface = LightOnSurface,

    error = ErrorLight,
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = ViridianLight,
    onPrimary = Color(0xFF00382B),
    primaryContainer = ViridianDark,
    onPrimaryContainer = ViridianLight,

    secondary = Peach,
    onSecondary = Color(0xFF3C2000),
    secondaryContainer = PeachDark,
    onSecondaryContainer = PeachLight,

    tertiary = Coral,
    onTertiary = Color(0xFF3C0A00),

    background = DarkBackground,
    onBackground = DarkOnBackground,
    surface = DarkSurface,
    onSurface = DarkOnSurface,

    error = ErrorDark,
    onError = Color.Black
)

@Composable
fun WaitWhatTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}