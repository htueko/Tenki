package com.htueko.tenki.feature.home.effect

/**
 * Sealed class representing the effects that can be triggered by the home screen feature.
 * The `ShowMessage` effect is used to display a message to the user.
 */
sealed class HomeEffect {
    data class ShowMessage(val message: String) : HomeEffect()
}