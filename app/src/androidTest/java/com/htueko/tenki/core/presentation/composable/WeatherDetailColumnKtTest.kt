package com.htueko.tenki.core.presentation.composable

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.assertCountEquals
import com.htueko.tenki.core.presentation.theme.TenkiTheme
import org.junit.Rule
import org.junit.Test

class WeatherDetailColumnTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun weatherDetailColumn_displaysTitleAndValue() {

        val title = "Temperature"
        val value = "25°C"

        composeTestRule.setContent {
            TenkiTheme {
                WeatherDetailColumn(
                    title = title,
                    value = value
                )
            }
        }

        composeTestRule.onNodeWithText(title)
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(value)
            .assertIsDisplayed()

        val titleNodes = composeTestRule.onAllNodesWithText(title).fetchSemanticsNodes()
        assert(titleNodes.size == 1) { "Expected exactly one title node, but found ${titleNodes.size}" }

        val valueNodes = composeTestRule.onAllNodesWithText(value).fetchSemanticsNodes()
        assert(valueNodes.size == 1) { "Expected exactly one value node, but found ${valueNodes.size}" }
    }
}
