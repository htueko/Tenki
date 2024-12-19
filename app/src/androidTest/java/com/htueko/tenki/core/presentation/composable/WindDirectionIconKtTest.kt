package com.htueko.tenki.core.presentation.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class WindDirectionIconTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun windDirectionIcon_displaysIcon() {
        composeTestRule.setContent {
            WindDirectionIcon(
                rotationDegrees = 0
            )
        }

        composeTestRule.onNodeWithContentDescription("0").assertIsDisplayed()
    }

    @Test
    fun windDirectionIcon_displaysIconWithRotation() {
        val rotationDegrees = 125

        composeTestRule.setContent {
            WindDirectionIcon(
                rotationDegrees = rotationDegrees
            )
        }

        composeTestRule.onNodeWithContentDescription(rotationDegrees.toString()).assertIsDisplayed()
    }
}
