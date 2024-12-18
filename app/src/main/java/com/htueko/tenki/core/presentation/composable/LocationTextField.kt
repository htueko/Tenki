package com.htueko.tenki.core.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.htueko.tenki.core.presentation.theme.Grey
import com.htueko.tenki.core.presentation.theme.SearchIconColour
import com.htueko.tenki.core.presentation.theme.TenkiTheme
import com.htueko.tenki.core.presentation.theme.TextFieldBackgroundColour
import com.htueko.tenki.core.presentation.theme.size

/**
 * Composable function that renders a location text field with a search icon.
 *
 * @param modifier The modifier to be applied to the text field.
 * @param value The current value of the text field.
 * @param onValueChange The callback function to be called when the text field value changes.
 * @param placeholder The placeholder text to be displayed in the text field.
 * @param onFocusChange The callback function to be called when the text field focus changes.
 */
@Composable
fun LocationTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    onFocusChange: (Boolean) -> Unit,
    onSearchIconClicked: () -> Unit,
) {
    val iconSize = MaterialTheme.size.twentyFour

    val backgroundColour = TextFieldBackgroundColour
    val searchIconColour = SearchIconColour
    val hintTextColour = SearchIconColour
    val focusTextColour = Grey

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                shape = MaterialTheme.shapes.medium
            )
            .background(
                backgroundColour,
                MaterialTheme.shapes.medium,
            )
            .focusRequester(focusRequester)
            .onFocusChanged { focusState ->
                onFocusChange(focusState.isFocused)
            }
            .onKeyEvent { keyEvent: KeyEvent ->
                // to handle the search action when the Enter key is pressed
                if (keyEvent.key == androidx.compose.ui.input.key.Key.Enter) {
                    onSearchIconClicked()
                    keyboardController?.hide()
                    focusRequester.freeFocus()
                    true
                } else {
                    false
                }
            },
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                color = hintTextColour
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = backgroundColour,
            unfocusedContainerColor = backgroundColour,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = focusTextColour,
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchIconClicked()
                keyboardController?.hide()
                focusManager.clearFocus()
                focusRequester.freeFocus()
            }
        ),
        trailingIcon = {
            IconButton(
                onClick = {
                    onSearchIconClicked()
                    focusRequester.freeFocus()
                    focusManager.clearFocus()
                    keyboardController?.hide()
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    modifier = Modifier.size(iconSize),
                    tint = searchIconColour
                )
            }
        },
    )

}

@Preview(name = "LocationTextField")
@Composable
private fun PreviewLocationTextField() {
    TenkiTheme {
        LocationTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = {},
            placeholder = "hint text",
            onFocusChange = {},
            onSearchIconClicked = {},
        )
    }
}