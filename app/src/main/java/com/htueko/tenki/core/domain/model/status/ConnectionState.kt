package com.htueko.tenki.core.domain.model.status

/**
 * ConnectionState is a sealed class that represents the state of network connectivity.
 *
 * It has two subclasses:
 * - [Available]: indicates that there is a network connection.
 * - [UnAvailable]: indicates that there is no network connection.
 *
 * @property Available: indicates that there is a network connection.
 * @property UnAvailable: indicates that there is no network connection.
 */
sealed class ConnectionState {
    /**
     * Represents that there is a network connection.
     */
    data object Available : ConnectionState()

    /**
     * Represents that there is no network connection.
     */
    data object UnAvailable : ConnectionState()
}