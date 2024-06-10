package com.example.androidad.components

import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.androidad.presentation.components.ReadOnlyTextField
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class ReadOnlyTextFieldTest {
    private val DISPLAY_TEXT = "Display Text"
    private var textInput = ""
    private val ERROR_MESSAGE_TEXT = "error displayed"
    private val errorIsNotPresent = true
    private val LABEL_TEXT = "Label Text"
    private var state: Boolean = false

    //Screen Elements to Test
    private val textToEnter = hasText(DISPLAY_TEXT)
    private val errorMessageText = hasText(ERROR_MESSAGE_TEXT)
    private val labelText = hasText(LABEL_TEXT)

    private val textfield = hasText(DISPLAY_TEXT) and hasClickAction()


    @get:Rule
    var rule = createComposeRule()


    @Test
    fun `check textfield with text present`() {
        rule.setContent {
            ReadOnlyTextField(
                text = DISPLAY_TEXT,
                onValueChange = { textInput = it },
                onClick = { state = true },
                errorMessage = ERROR_MESSAGE_TEXT,
                errorPresent = errorIsNotPresent,
                showError = false,
                label = { labelText }
            )
        }
        rule.onNode(textToEnter).assertExists()
        rule.onNodeWithText(ERROR_MESSAGE_TEXT).assertDoesNotExist()
        assertTrue(errorIsNotPresent)
    }

    @Test
    fun `test with error displays error`() {
        rule.setContent {
            ReadOnlyTextField(
                text = "",
                onValueChange = { textInput = it },
                onClick = { state = true },
                errorMessage = ERROR_MESSAGE_TEXT,
                errorPresent = true,
                showError = true,
                label = { labelText }
            )
        }
        rule.onNodeWithText("").assertExists()
        rule.onNode(errorMessageText).assertExists()
    }

    @Test
    fun `text field is clickable`() {
        rule.setContent {
            ReadOnlyTextField(
                text = DISPLAY_TEXT,
                onValueChange = { textInput = it },
                onClick = { state = true },
                errorMessage = ERROR_MESSAGE_TEXT,
                errorPresent = errorIsNotPresent,
                showError = false,
                label = { labelText }
            )
        }
        rule.onNode(textfield).assertExists()
        rule.onNode(textfield).performClick()
        assertTrue(state)
    }

    @Test
    fun `text field is read only`() {
        rule.setContent {
            ReadOnlyTextField(
                text = DISPLAY_TEXT,
                onValueChange = { textInput = it },
                onClick = { state = true },
                errorMessage = ERROR_MESSAGE_TEXT,
                errorPresent = errorIsNotPresent,
                showError = false,
                label = { labelText }
            )
        }
        val ADDITIONAL_TEXT = "additional text"
        rule.onNode(textToEnter).assertExists()
        rule.onNode(textToEnter).performTextInput(ADDITIONAL_TEXT)

        rule.onNode(errorMessageText).assertDoesNotExist()
        assertTrue(errorIsNotPresent)

        Assert.assertEquals(textInput, "")
        Assert.assertEquals("Display Text", DISPLAY_TEXT)
    }
}