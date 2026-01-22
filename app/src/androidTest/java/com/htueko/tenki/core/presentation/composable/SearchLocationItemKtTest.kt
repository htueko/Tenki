package com.htueko.tenki.core.presentation.composable

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Rule
import org.junit.Test

class SearchLocationItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSearchLocationItemClick() {

        var clickedCity: String? = null
        val cityName = "Tip Top"
        val temperature = "25°C"
        val iconUrl = "sample_url"
        val condition = "sunny"

        composeTestRule.setContent {
            SearchLocationItem(
                city = cityName,
                temperature = temperature,
                iconUrl = iconUrl,
                condition = condition,
                onClicked = { clickedCity = it }
            )
        }

        composeTestRule.onNodeWithText(cityName)
            .performClick()

        assert(clickedCity == cityName) { "Expected city name to be $cityName, but was $clickedCity" }
    }

    @Test
    fun testSearchLocationItemDisplaysCorrectly() {
        val cityName = "Tip Top"
        val temperature = "25°C"
        val iconUrl = "sample_url"
        val condition = "sunny"

        composeTestRule.setContent {
            SearchLocationItem(
                city = cityName,
                temperature = temperature,
                iconUrl = iconUrl,
                condition = condition,
                onClicked = {}
            )
        }

        composeTestRule.onNodeWithText(cityName).assertExists("City name not found")
        composeTestRule.onNodeWithText(temperature).assertExists("Temperature not found")

        composeTestRule.onNodeWithContentDescription(condition).assertExists("Weather icon not found")

        composeTestRule.onNodeWithText(cityName).assertIsDisplayed()
        composeTestRule.onNodeWithText(temperature).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(condition).assertIsDisplayed()
    }
}
