package com.htueko.tenki.core.presentation.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import com.htueko.tenki.core.domain.model.status.ConnectionState
import com.htueko.tenki.core.util.currentConnectivityState
import com.htueko.tenki.core.util.observeConnectivityAsFlow
import timber.log.Timber

@Composable
fun ConnectivityObserver(
    ConnectedContent:
    @Composable () -> Unit,
    DisconnectedContent:
    @Composable () -> Unit,
) {
    if (LocalInspectionMode.current) {
        ConnectedContent()
    } else {
        val connection by connectivityStateComposable()
        Timber.tag("networkObserver").d("available: $connection")
        when (connection) {
            ConnectionState.Available -> {
                ConnectedContent()
            }

            ConnectionState.UnAvailable -> {
                DisconnectedContent()
            }
        }
    }
}

/**
 * A composable function that returns a State object of type ConnectionState. This state object
 * represents the current connectivity state of the device.
 *
 * @return A State object of type ConnectionState representing the current connectivity state.
 */
@Composable
fun connectivityStateComposable(): State<ConnectionState> {
    // Get the current application context
    val context = LocalContext.current

    // Create a State object with the initial value of the current connectivity state
    return produceState(context.currentConnectivityState) {
        // Observe changes in the connectivity state using the observeConnectivityAsFlow() function
        // and update the state object whenever a new state is emitted.
        context.observeConnectivityAsFlow().collect { connectionState ->
            value = connectionState
        }
    }
}