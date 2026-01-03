package com.example.ui.theme.colors

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * material theme color palette.
 * Provides custom color variants that are not in the Material Theme.
 */
object AppColorSchema {
    val accentColor = Color(0xFF2ABA90)
    val red = Color(0xFFEF2F2F)
    val darkGray = Color(0xFF353434)
    val acceptColor = Color(0xFF0FA939)

    val accentTextLight = Color(0xFFFFFFFF)
    val contentTextLight = Color(0xFF1E1E1E)
    val contentBgLight = Color(0xFFFFFFFF)
    val contentStrokeLight = Color(0xFF717171)
    val contentStroke2Light = Color(0xFFDDDCDC)
    val contentStroke3Light = Color(0xFFF6F6F6)
    val routeLineLight = Color(0xFF1E1E1E)
    val mapMarkerBgLight = Color(0xFF1E1E1E)
    val mapMarkerBgStrokeLight = Color(0xFFEFEFEF)

    val accentTextDark = Color(0xFFFFFFFF)
    val contentTextDark = Color(0xFFEFEFEF)
    val contentBgDark = Color(0xFF1E1E1E)
    val contentStrokeDark = Color(0xFFAEAEAE)
    val contentStroke2Dark = Color(0xFF535353)
    val contentStroke3Dark = Color(0xFF353434)
    val routeLineDark = Color(0xFFFFFFFF)
    val mapMarkerBgDark = Color(0xFF1E1E1E)
    val mapMarkerBgStrokeDark = Color(0xFFEFEFEF)

    private val WhiteTransparent = Color(0x44FFFFFF)
}
/**
 * Custom color palette for the light theme.
 */

val lightExtendedColors =
    AppColors(
        accentText = AppColorSchema.accentTextLight,
        contentText = AppColorSchema.contentTextLight,
        contentBg = AppColorSchema.contentBgLight,
        contentStroke = AppColorSchema.contentStrokeLight,
        contentStroke2 = AppColorSchema.contentStroke2Light,
        contentStroke3 = AppColorSchema.contentStroke3Light,
        routeLine = AppColorSchema.routeLineLight,
        mapMarkerBg = AppColorSchema.mapMarkerBgLight,
        mapMarkerBgStroke = AppColorSchema.mapMarkerBgStrokeLight,
    )

val darkExtendedColors =
    AppColors(
        accentText = AppColorSchema.accentTextDark,
        contentText = AppColorSchema.contentTextDark,
        contentBg = AppColorSchema.contentBgDark,
        contentStroke = AppColorSchema.contentStrokeDark,
        contentStroke2 = AppColorSchema.contentStroke2Dark,
        contentStroke3 = AppColorSchema.contentStroke3Dark,
        routeLine = AppColorSchema.routeLineDark,
        mapMarkerBg = AppColorSchema.mapMarkerBgDark,
        mapMarkerBgStroke = AppColorSchema.mapMarkerBgStrokeDark,
    )

/**
 * A [CompositionLocal] that provides the light theme color palette.
 */

val LocalAppColors = staticCompositionLocalOf { lightExtendedColors }