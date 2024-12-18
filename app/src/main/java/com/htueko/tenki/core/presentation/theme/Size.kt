package com.htueko.tenki.core.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A data class that defines various size values in Density-independent Pixels (Dp) for use in the app's UI.
 * These values are used to ensure consistent spacing and sizing throughout the app's components.
 */
data class Size(
    val zero: Dp = 0.dp,
    val two: Dp = 2.dp,
    val four: Dp = 4.dp,
    val eight: Dp = 8.dp,
    val sixteen: Dp = 16.dp,
    val twentyFour: Dp = 24.dp,
    val thirtyTwo: Dp = 32.dp,
    val thirtySix: Dp = 36.dp,
    val fourthEight: Dp = 48.dp,
    val fiftySix: Dp = 56.dp,
    val sixtyFour: Dp = 64.dp,
    val twentyFive: Dp = 25.dp,
    val fortyFive: Dp = 45.dp,
    val fifty: Dp = 50.dp,
    val fiftyTwo: Dp = 52.dp,
)

val LocalSize = androidx.compose.runtime.compositionLocalOf { Size() }

val MaterialTheme.size: Size
    @Composable
    @ReadOnlyComposable
    get() = LocalSize.current