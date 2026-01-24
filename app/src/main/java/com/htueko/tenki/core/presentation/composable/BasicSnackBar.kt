package com.htueko.tenki.core.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.htueko.tenki.core.presentation.theme.padding

/**
 * Displays a basic snackbar with a message and an optional action button.
 *
 * @param modifier The modifier to be applied to the snackbar.
 * @param snackbarHostState The state of the snackbar.
 */
@Composable
fun BasicSnackBar(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
) {

    val paddingEight = MaterialTheme.padding.eight

    Box(
        modifier =
        modifier
            .fillMaxWidth(),
    ) {

        SnackbarHost(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .wrapContentHeight(Alignment.Bottom)
                .fillMaxWidth(),
            hostState = snackbarHostState,
        ) { data ->

            Snackbar(
                snackbarData = data,
                containerColor = MaterialTheme.colorScheme.onBackground,
                contentColor = MaterialTheme.colorScheme.background,
                actionColor = MaterialTheme.colorScheme.background,
            )

        }
    }
}