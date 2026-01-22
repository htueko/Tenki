package com.htueko.tenki.core.presentation.composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class LocationTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun locationTextField_DisplaysPlaceholder() {
        val placeholder = "Enter location"

        composeTestRule.setContent {
            LocationTextField(
                value = "",
                onValueChange = {},
                placeholder = placeholder,
                onFocusChange = {},
                onSearchIconClicked = {}
            )
        }

        composeTestRule.onNodeWithText(placeholder).assertIsDisplayed()
    }

    @Test
    fun locationTextField_UpdatesTextValue() {
        val inputText = "London"
        val textFieldValue = mutableStateOf("")

        composeTestRule.setContent {
            LocationTextField(
                value = textFieldValue.value,
                onValueChange = { textFieldValue.value = it },
                placeholder = "Enter location",
                onFocusChange = {},
                onSearchIconClicked = {}
            )
        }

        composeTestRule.onNodeWithText("Enter location").performTextInput(inputText)

        composeTestRule.onNodeWithText(inputText).assertExists()
    }

    @Test
    fun locationTextField_SearchIconClickable() {
        var searchClicked = false

        composeTestRule.setContent {
            LocationTextField(
                value = "",
                onValueChange = {},
                placeholder = "Enter location",
                onFocusChange = {},
                onSearchIconClicked = { searchClicked = true }
            )
        }

        composeTestRule.onNodeWithContentDescription("Search").performClick()
        assert(searchClicked)
    }

    @Test
    fun locationTextField_FocusChangeTriggered() {
        var isFocused = false

        composeTestRule.setContent {
            LocationTextField(
                value = "",
                onValueChange = {},
                placeholder = "Enter location",
                onFocusChange = { isFocused = it },
                onSearchIconClicked = {}
            )
        }

        composeTestRule.onNodeWithText("Enter location").performClick()
        assert(isFocused)
    }
}
