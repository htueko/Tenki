package com.htueko.tenki.feature.home.effect

sealed class HomeEffect {
    data object ShowMessage : HomeEffect()
}