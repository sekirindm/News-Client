package com.example.ui.theme.colors

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class AppColors(
    val accentText: Color,
    val contentText: Color,

    val contentBg: Color,

    val contentStroke: Color,
    val contentStroke2: Color,
    val contentStroke3: Color,

    val routeLine: Color,
    val mapMarkerBg: Color,
    val mapMarkerBgStroke: Color
)