package com.htueko.tenki.core.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.htueko.tenki.core.presentation.theme.TenkiTheme

/**
 * Displays a full-screen loading indicator with a centered circular progress indicator.
 *
 * @param modifier Optional modifier to be applied to the layout.
 */
@Composable
fun FullScreenLoadingIndicator(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.semantics { contentDescription = "Loading" }
        )
    }
}

@Preview(name = "FullScreenLoadingIndicator")
@Composable
private fun PreviewFullScreenLoadingIndicator() {
    TenkiTheme {
        FullScreenLoadingIndicator()
    }
}