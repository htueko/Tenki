package com.htueko.tenki.core.presentation.composable


import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Rule
import org.junit.Test

class FullScreenLoadingIndicatorTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun fullScreenLoadingIndicator_DisplaysProgressIndicator() {
        composeTestRule.setContent {
            FullScreenLoadingIndicator()
        }

        composeTestRule.onNodeWithContentDescription("Loading").assertIsDisplayed()
    }
}
