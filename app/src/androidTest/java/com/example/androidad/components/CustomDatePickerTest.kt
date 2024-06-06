package com.example.androidad.components

import androidx.compose.material3.Text
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.androidad.presentation.components.DatePickerWithDialog
import org.junit.Rule
import org.junit.Test

class CustomDatePickerTest {

    @get:Rule
    var rule = createComposeRule()

    private val LABEL_TEXT = "Label"
    private val ERROR_MESSAGE_TEXT = "error displayed"
    private val DATE_TEXT = "Date"
    private var dateInput = ""

    private val labelText = hasText(LABEL_TEXT)
    private val placeholderText = hasText(DATE_TEXT)
    private val errorMessageText = hasText(ERROR_MESSAGE_TEXT)

    @Test
    fun `date picker dialog is not shown initially`(){
        rule.setContent {
            DatePickerWithDialog(
                text = DATE_TEXT,
                onDateSelected = {},
                onValueChange = { dateInput = it},
                label = { LABEL_TEXT },
                errorMessage = ERROR_MESSAGE_TEXT,
                errorPresent = false,
                showError = false
            )
        }
        rule.onNode(placeholderText).assertExists()
        rule.onNodeWithText("OK").assertDoesNotExist()
        rule.onNodeWithText("Cancel").assertDoesNotExist()
    }

    @Test
    fun `date picker dialog opens on click`(){
        rule.setContent {
            DatePickerWithDialog(
                text = DATE_TEXT,
                onDateSelected = {},
                onValueChange = { dateInput = it},
                label = { LABEL_TEXT },
                errorMessage = ERROR_MESSAGE_TEXT,
                errorPresent = false,
                showError = false
            )
        }
        rule.onNode(placeholderText).performClick()
        rule.onNodeWithText("OK").assertExists()
        rule.onNodeWithText("Cancel").assertExists()
    }

    @Test
    fun `date picker dialog closes on cancel`(){
        rule.setContent {
            DatePickerWithDialog(
                text = DATE_TEXT,
                onDateSelected = {},
                onValueChange = { dateInput = it},
                label = { LABEL_TEXT },
                errorMessage = ERROR_MESSAGE_TEXT,
                errorPresent = false,
                showError = false
            )
        }
        rule.onNode(placeholderText).performClick()
        rule.onNodeWithText("Cancel").performClick()
        rule.onNodeWithText("OK").assertDoesNotExist()
        rule.onNodeWithText("Cancel").assertDoesNotExist()
    }

    @Test
    fun `date picker selects date on confirm`(){
        var selectedDate = ""
        rule.setContent {
            DatePickerWithDialog(
                text = DATE_TEXT,
                onDateSelected = {selectedDate = it},
                onValueChange = { dateInput = it},
                label = { LABEL_TEXT },
                errorMessage = ERROR_MESSAGE_TEXT,
                errorPresent = false,
                showError = false
            )
        }
        rule.onNode(placeholderText).performClick()
        rule.onNodeWithText("OK").performClick()
        rule.runOnIdle {
            assert(selectedDate.isNotEmpty()) { "Selected date should not be empty" }
        }
        rule.onNodeWithText("OK").assertDoesNotExist()
        rule.onNodeWithText("Cancel").assertDoesNotExist()

    }
}