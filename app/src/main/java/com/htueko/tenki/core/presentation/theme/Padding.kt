package com.htueko.tenki.core.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 * A data class that defines various padding values for use in the application's UI.
 * These values are used to ensure consistent spacing and layout across the app.
 */
data class Padding(
    val zero: Dp = 0.dp,
    val two: Dp = 2.dp,
    val four: Dp = 4.dp,
    val five: Dp = 5.dp,
    val six: Dp = 6.dp,
    val eight: Dp = 8.dp,
    val ten: Dp = 10.dp,
    val twelve: Dp = 12.dp,
    val fourTeen: Dp = 14.dp,
    val fifteen: Dp = 15.dp,
    val sixteen: Dp = 16.dp,
    val seventeen: Dp = 17.dp,
    val eighteen: Dp = 18.dp,
    val twenty: Dp = 20.dp,
    val twentyTwo: Dp = 22.dp,
    val twentyFour: Dp = 24.dp,
    val twentySix: Dp = 26.dp,
    val twentySeven: Dp = 27.dp,
    val twentyEight: Dp = 28.dp,
    val thirty: Dp = 30.dp,
    val thirtyTwo: Dp = 32.dp,
    val thirtyFour: Dp = 34.dp,
    val thirtyFive: Dp = 35.dp,
    val thirtySix: Dp = 36.dp,
    val thirtySeven: Dp = 37.dp,
    val forty: Dp = 40.dp,
    val fortyThree: Dp = 43.dp,
    val fortyFour: Dp = 44.dp,
    val fortyFive: Dp = 45.dp,
    val fortySix: Dp = 46.dp,
    val fortySeven: Dp = 47.dp,
    val fourthEight: Dp = 48.dp,
    val fifty: Dp = 50.dp,
    val fiftySix: Dp = 56.dp,
    val sixtyFour: Dp = 64.dp,
)

val LocalPadding = compositionLocalOf { Padding() }

val MaterialTheme.padding: Padding
    @Composable
    @ReadOnlyComposable
    get() = LocalPadding.current
