package com.htueko.tenki.core.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.htueko.tenki.core.domain.model.status.ConnectionState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

val Context.currentConnectivityState: ConnectionState
    get() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return getCurrentConnectivityState(connectivityManager)
    }

/**
 * Retrieves the current connectivity state of the device.
 *
 * This function takes a [ConnectivityManager] as a parameter and uses it to get the currently active
 * network and its capabilities. It then checks the transport type of the network to determine the
 * connectivity state. If the network is using Wi-Fi, cellular, or Ethernet, it returns [ConnectionState.Available].
 * If the network is not available or is using an unsupported transport type, it returns [ConnectionState.UnAvailable].
 *
 * @param cm The [ConnectivityManager] used to retrieve the current network and its capabilities.
 * @return The current connectivity state of the device.
 */
private fun getCurrentConnectivityState(cm: ConnectivityManager): ConnectionState {
    val networkCapabilities = cm.activeNetwork
    val activeNetwork = cm.getNetworkCapabilities(networkCapabilities)
    return when {
        activeNetwork == null -> ConnectionState.UnAvailable
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> ConnectionState.Available
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> ConnectionState.Available
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> ConnectionState.Available
        else -> ConnectionState.UnAvailable
    }
}

/**
 * Returns a [ConnectivityManager.NetworkCallback] that calls the given callback function
 * with the current connectivity state whenever the network state changes.
 *
 * @param callback The function to call with the current connectivity state.
 * @return A [ConnectivityManager.NetworkCallback] that calls the given callback function.
 */
fun networkCallBack(callback: (ConnectionState) -> Unit): ConnectivityManager.NetworkCallback =
    object : ConnectivityManager.NetworkCallback() {
        /**
         * Calls the callback function with [ConnectionState.UnAvailable] when the network is lost.
         *
         * @param network The network that was lost.
         */
        override fun onLost(network: Network) {
            super.onLost(network)
            callback(ConnectionState.UnAvailable)
        }

        /**
         * Calls the callback function with [ConnectionState.Available] when the network is available.
         *
         * @param network The network that became available.
         */
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            callback(ConnectionState.Available)
        }
    }

/**
 * Returns a cold [Flow] that emits the current and subsequent connectivity states
 * whenever the network state changes.
 *
 * The [Flow] is implemented as a [callbackFlow] that registers a network callback
 * with the system's [ConnectivityManager] and starts emitting values when collector
 * subscribes to the [Flow].
 *
 * When the collector cancels the subscription, the network callback is unregistered
 * to prevent memory leaks.
 *
 * @return A cold [Flow] that emits the current and subsequent connectivity states.
 */
fun Context.observeConnectivityAsFlow() =
    callbackFlow {
        // Get the system service for connectivity
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Define the network callback that calls the collector with the current connectivity state
        val callback = networkCallBack { state -> trySend(state) }

        // Create a network request that specifies the transport type and capabilities
        val networkRequest =
            NetworkRequest
                .Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()

        // Register the network callback with the connectivity manager
        cm.registerNetworkCallback(networkRequest, callback)

        // Emit the current connectivity state to the collector
        val currentState = getCurrentConnectivityState(cm)
        trySend(currentState)

        // Wait for the collector to cancel the subscription and then unregister the network callback
        awaitClose {
            cm.unregisterNetworkCallback(callback)
        }
    }
