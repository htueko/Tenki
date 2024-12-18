package com.htueko.tenki.core.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A data class that defines various corner radius values for use in a Material Design 3 theme.
 * The values are provided in Dp (density-independent pixels) and a round value is also included.
 * These values can be used to consistently apply corner radii across the UI components in the app.
 */
data class CornerRadius(
    val half: Dp = 0.5.dp,
    val zero: Dp = 0.dp,
    val one: Dp = 1.dp,
    val two: Dp = 2.dp,
    val three: Dp = 3.dp,
    val four: Dp = 4.dp,
    val five: Dp = 5.dp,
    val six: Dp = 6.dp,
    val eight: Dp = 8.dp,
    val ten: Dp = 10.dp,
    val twelve: Dp = 12.dp,
    val fourteen: Dp = 14.dp,
    val sixteen: Dp = 16.dp,
    val twenty: Dp = 20.dp,
    val twentyFour: Dp = 24.dp,
    val thirtyTwo: Dp = 32.dp,
    val fiftySix: Dp = 56.dp,
    val sixtyFour: Dp = 64.dp,
    val round: Float = 50f,
)

val LocalCornerRadius = compositionLocalOf { CornerRadius() }

val MaterialTheme.cornerRadius: CornerRadius
    @Composable
    @ReadOnlyComposable
    get() = LocalCornerRadius.current
