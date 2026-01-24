package com.htueko.tenki.core.presentation.composable

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.assertCountEquals
import com.htueko.tenki.core.presentation.theme.TenkiTheme
import org.junit.Rule
import org.junit.Test

class WeatherDetailsCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun weatherDetailsCard_displaysHumidityUVAndFeelsLike() {

        val humidityValue = "75"
        val uvValue = "5"
        val feelsLikeValue = "25"

        composeTestRule.setContent {
            TenkiTheme {
                WeatherDetailsCard(
                    humidityValue = humidityValue,
                    uvValue = uvValue,
                    feelsLikeValue = feelsLikeValue
                )
            }
        }


        composeTestRule.onNodeWithText("Humidity")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("$humidityValue%")
            .assertIsDisplayed()


        composeTestRule.onNodeWithText("UV")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(uvValue)
            .assertIsDisplayed()


        composeTestRule.onNodeWithText("Feels Like")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("$feelsLikeValue°")
            .assertIsDisplayed()


        val humidityNodes = composeTestRule.onAllNodesWithText("$humidityValue%").fetchSemanticsNodes()
        assert(humidityNodes.size == 1) { "Expected exactly one humidity node, but found ${humidityNodes.size}" }

        val uvNodes = composeTestRule.onAllNodesWithText(uvValue).fetchSemanticsNodes()
        assert(uvNodes.size == 1) { "Expected exactly one UV node, but found ${uvNodes.size}" }

        val feelsLikeNodes = composeTestRule.onAllNodesWithText("$feelsLikeValue°").fetchSemanticsNodes()
        assert(feelsLikeNodes.size == 1) { "Expected exactly one feels like node, but found ${feelsLikeNodes.size}" }
    }
}
