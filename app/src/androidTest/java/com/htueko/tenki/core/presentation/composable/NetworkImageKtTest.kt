package com.htueko.tenki.core.presentation.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Rule
import org.junit.Test

class NetworkImageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun networkImage_displaysLoadingIndicator() {
        composeTestRule.setContent {
            NetworkImage(
                imageUrl = "https://pixabay.com/vectors/cute-cat-animal-forest-cartoon-7270285/",
                contentDescription = "Loading Image"
            )
        }

        // Assert that the loading indicator is displayed
        composeTestRule.onNodeWithContentDescription("Loading Image").assertIsDisplayed()
    }

    @Test
    fun networkImage_displaysImageAfterLoading() {
        composeTestRule.setContent {
            NetworkImage(
                imageUrl = "https://pixabay.com/vectors/cute-cat-animal-forest-cartoon-7270285/",
                contentDescription = "Loaded Image"
            )
        }

        // Simulate image loading success
        composeTestRule.onNodeWithContentDescription("Loaded Image").assertIsDisplayed()
    }
}
