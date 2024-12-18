package com.htueko.tenki.core.presentation.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class WeatherConditionCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun weatherConditionCard_displaysWeatherIcon() {
        composeTestRule.setContent {
            WeatherConditionCard(
                iconUrl = "https://pixabay.com/vectors/cute-cat-animal-forest-cartoon-7270285/",
                weatherCondition = "Sunny",
                locationName = "Tokyo",
                windDirection = 90,
                temperature = 25.0,
            )
        }

        composeTestRule.onNodeWithContentDescription("Sunny").assertIsDisplayed()
    }

    @Test
    fun weatherConditionCard_displaysLocationName() {
        composeTestRule.setContent {
            WeatherConditionCard(
                iconUrl = "https://pixabay.com/vectors/cute-cat-animal-forest-cartoon-7270285/",
                weatherCondition = "Sunny",
                locationName = "Tokyo",
                windDirection = 90,
                temperature = 25.0,
            )
        }

        composeTestRule.onNodeWithText("Tokyo").assertIsDisplayed()
    }

    @Test
    fun weatherConditionCard_displaysTemperature() {
        composeTestRule.setContent {
            WeatherConditionCard(
                iconUrl = "https://pixabay.com/vectors/cute-cat-animal-forest-cartoon-7270285/",
                weatherCondition = "Sunny",
                locationName = "Tokyo",
                windDirection = 90,
                temperature = 25.0,
            )
        }

        composeTestRule.onNodeWithText("25°").assertIsDisplayed()
    }

}
