package com.htueko.tenki.feature.home.effect

sealed class HomeEffect {
    data class ShowMessage(val message: String) : HomeEffect()
}