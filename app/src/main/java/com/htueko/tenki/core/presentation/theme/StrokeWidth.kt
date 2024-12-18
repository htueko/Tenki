package com.htueko.tenki.core.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 * A data class that holds different stroke width values as Dp (density-independent pixel) values.
 * These values can be used to set the stroke width of UI elements in a Compose-based application.
 */
data class StrokeWidth(
    val zero: Dp = 0.dp,
    val one: Dp = 1.dp,
    val two: Dp = 2.dp,
    val three: Dp = 3.dp,
    val four: Dp = 4.dp,
)

val LocalStrokeWidth = compositionLocalOf { StrokeWidth() }

val MaterialTheme.strokeWidth: StrokeWidth
    @Composable
    @ReadOnlyComposable
    get() = LocalStrokeWidth.current
