package com.example.androidad.components

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.androidad.presentation.components.CustomTextField
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class CustomTextFieldTest {

    //Initialise Data
    private val HINT_TEXT = "hint text"
    private val DISPLAY_TEXT = "Display Text"
    private var textInput = ""
    private val ERROR_MESSAGE_TEXT = "error displayed"
    private val errorIsNotPresent = true

    //Screen Elements to Test
    private val textToEnter = hasText(DISPLAY_TEXT)
    private val hintText = hasText(HINT_TEXT)
    private val errorMessageText = hasText(ERROR_MESSAGE_TEXT)


    @get:Rule
    var rule = createComposeRule()

    @Test
    fun `check state of textfield with text present`() {
        rule.setContent {
            CustomTextField(
                hintText = HINT_TEXT,
                text = DISPLAY_TEXT,
                onValueChange = { textInput = it },
                errorMessage = ERROR_MESSAGE_TEXT,
                errorPresent = errorIsNotPresent,
                showError = false
            )
        }
        rule.onNode(hintText).assertExists()
        rule.onNode(textToEnter).assertExists()
        rule.onNodeWithText(ERROR_MESSAGE_TEXT).assertDoesNotExist()
        assertTrue(errorIsNotPresent)
    }

    @Test
    fun `test with additoinal text`() {
        rule.setContent {
            CustomTextField(
                hintText = HINT_TEXT,
                text = DISPLAY_TEXT,
                onValueChange = { textInput = it },
                errorMessage = ERROR_MESSAGE_TEXT,
                errorPresent = errorIsNotPresent,
                showError = false
            )
        }
        val ADDITIONAL_TEXT = "additional text"
        rule.onNode(hintText).assertExists()
        rule.onNode(textToEnter).performTextInput(ADDITIONAL_TEXT)

        rule.onNode(errorMessageText).assertDoesNotExist()
        assertTrue(errorIsNotPresent)

        assertEquals(textInput, ADDITIONAL_TEXT.plus(DISPLAY_TEXT))
    }

    @Test
    fun `test with error displays error message`() {
        rule.setContent {
            CustomTextField(
                hintText = HINT_TEXT,
                text = "",
                onValueChange = { textInput = it },
                errorMessage = ERROR_MESSAGE_TEXT,
                errorPresent = true,
                showError = true
            )
        }
        rule.onNodeWithText("").assertExists()
        rule.onNode(errorMessageText).assertExists()
    }

    @Test
    fun `displays astrix with password set to true`() {
        var textInput = "password"

        rule.setContent {
            CustomTextField(
                hintText = HINT_TEXT,
                text = textInput,
                isPasswordField = true,
                onValueChange = { textInput = it },
                errorMessage = ERROR_MESSAGE_TEXT,
                errorPresent = errorIsNotPresent,
                showError = false
            )
        }
        textInput = textInput.replaceRange(0, textInput.length, "*")
        rule.onNodeWithText("********").assertExists()
    }

    @Test
    fun `textInput cannot be changed when read only`() {
        rule.setContent {
            CustomTextField(
                hintText = HINT_TEXT,
                text = DISPLAY_TEXT,
                onValueChange = { textInput = it },
                errorMessage = ERROR_MESSAGE_TEXT,
                errorPresent = errorIsNotPresent,
                showError = false,
                readOnly = true
            )
        }
        val ADDITIONAL_TEXT = "additional text"
        rule.onNode(hintText).assertExists()
        rule.onNode(textToEnter).performTextInput(ADDITIONAL_TEXT)

        rule.onNode(errorMessageText).assertDoesNotExist()
        assertTrue(errorIsNotPresent)

        assertEquals(textInput, "")
        assertEquals("Display Text", DISPLAY_TEXT)
    }


    @Test
    fun ` text spans multiple lines when singleLine is false`() {
        rule.setContent {
            CustomTextField(
                hintText = HINT_TEXT,
                text = DISPLAY_TEXT,
                onValueChange = { textInput = it },
                errorMessage = ERROR_MESSAGE_TEXT,
                errorPresent = errorIsNotPresent,
                showError = false,
                readOnly = false,
                singleLine = false
            )
        }
        val MULTILINE_TEXT = "Line1\nLine2\nLine3"
        rule.onNode(hintText).assertExists()
        rule.onNode(textToEnter).performTextInput(MULTILINE_TEXT)
        assertEquals(MULTILINE_TEXT.plus(DISPLAY_TEXT), textInput)
    }


}
