package com.example.androidad.screens

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Test

class EditScreenTest : ScreenTests() {
    @Before
    override fun setUp() {
        super.setUp()
    }

    fun goToEditScreen() {
        loginNotAdmin()
        rule.onNode(listItem).performClick()
        rule.onNode(editButton).performClick()
    }

    @Test
    fun `check default state of edit screen`() {
        goToEditScreen()
        rule.onNode(editScreenTitle).assertExists()
        rule.onNode(firstaiderTextField).assertTextEquals(FIRSTAIDER)
        rule.onNode(locationTextField).assertTextEquals(LOCATION)
        rule.onNode(dateTextField).assertTextEquals(DATE)
        rule.onNode(timeTextField).assertTextEquals(TIME)
        rule.onNode(injuredPartyTextField).assertTextEquals(INJUREDPARTY)
        rule.onNode(injuryTextField).assertTextEquals(INJURY)
        rule.onNode(treatmentTextField).assertTextEquals(TREATMENT)
        rule.onNode(adviceTextField).assertTextEquals(ADVICE)
    }

    @Test
    fun `can edit information`() {
        goToEditScreen()
        rule.onNode(locationTextField).performTextInput(EDIT_LOCATION)
        rule.onNode(dateTextField).performClick()
        rule.onNodeWithText(EDIT_DATE).performClick()
        rule.onNodeWithText("OK").performClick()
        rule.onNode(timeTextField).performTextInput(EDIT_TIME)
        rule.onNode(injuredPartyTextField).performTextInput(EDIT_INJUREDPARTY)
        rule.onNode(injuryTextField).performTextInput(EDIT_INJURY)
        rule.onNode(treatmentTextField).performTextInput(EDIT_TREATMENT)
        rule.onNode(adviceTextField).performTextInput(EDIT_ADVICE)
        rule.onNode(confirmChangesButton).performClick()

        rule.onNode(editListItem).assertExists()
    }

    @Test
    fun `delete report`() {
        enter_a_valid_report()
        rule.onNode(listItem).performClick()
        rule.onNode(editButton).performClick()
        rule.onNode(deleteButton).performClick()
        rule.onNode(listItem).assertDoesNotExist()
    }
}