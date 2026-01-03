package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.example.ui.theme.colors.LocalAppColors
import com.example.ui.theme.colors.lightExtendedColors
import com.example.ui.theme.shapes.LocalAppShapes
import com.example.ui.theme.shapes.appShapes
import com.example.ui.theme.spacing.ExtendedSpacing
import com.example.ui.theme.spacing.LocalAppSpacing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.example.ui.theme.colors.darkExtendedColors
import com.example.ui.theme.typography.AppTypography
import com.example.ui.theme.typography.LocalAppTypography

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1E1E1E),
    onPrimary = Color.White,
    background = Color.White,
    onBackground = Color(0xFF1E1E1E),
    surface = Color.White,
    onSurface = Color(0xFF1E1E1E),
    secondary = Color(0xFF535353),
    onSecondary = Color.White,
)

private val DarkColorScheme = darkColorScheme(
    primary = Color.White,
    onPrimary = Color(0xFF1E1E1E),
    background = Color(0xFF1E1E1E),
    onBackground = Color.White,
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White,
    secondary = Color(0xFFDDDCDC),
    onSecondary = Color(0xFF1E1E1E),
)

/**
 * App custom theme for the entire Compose hierarchy.
 * @param darkTheme Whether to apply the dark theme (true) or the light theme (false). Default value is determined by the system's dark theme setting.
 * @param content The content that should be displayed within the theme.
 */
@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) DarkColorScheme else LightColorScheme
    val extendedColors = if (darkTheme) darkExtendedColors else lightExtendedColors
    // CompositionLocalProvider is used to provide custom theme values to the Compose hierarchy.
    CompositionLocalProvider(
        // Provide the extended colors based on the darkTheme parameter.
        LocalAppColors provides extendedColors,
        // Provide the custom shapes defined by appShapes.
        LocalAppShapes provides appShapes,
        // Provide the custom spacing values.
        LocalAppSpacing provides ExtendedSpacing(),
        // Provide the custom typography styles.
        LocalAppTypography provides AppTypography(),
    ) {
        // Apply the MaterialTheme with the custom typography and the content provided in the composable.
        MaterialTheme(
            content = content,
            colorScheme = colors
        )
    }
}