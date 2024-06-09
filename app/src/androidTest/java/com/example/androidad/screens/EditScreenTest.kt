package com.example.androidad.screens

import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.example.androidad.R
import org.junit.Before
import org.junit.Test

class EditScreenTest : ScreenTests() {
    @Before
    override fun setUp() {
        super.setUp()
    }


    @Test
    fun `check default state of edit screen`() {
        loginNotAdmin()
        goToEditScreen()
        rule.onNode(editScreenTitle).assertExists()
        rule.onNodeWithText(FIRSTAIDER).assertExists()
        rule.onNodeWithText(LOCATIONEDIT).assertExists()
        rule.onNodeWithText(DATE).assertExists()
        rule.onNodeWithText(TIME).assertExists()
        rule.onNodeWithText(INJUREDPARTY).assertExists()
        rule.onNodeWithText(INJURY).assertExists()
        rule.onNodeWithText(TREATMENT).assertExists()
        rule.onNodeWithText(ADVICE).assertExists()
        rule.onNode(deleteButton).performClick()


    }

    @Test
    fun `can edit information`() {
        loginNotAdmin()
        goToEditScreen()
        rule.onNode(locationTextField).performTextClearance()
        rule.onNode(locationTextField).performTextInput(EDIT_LOCATION)
        rule.onNode(dateTextField).performClick()
        rule.onNodeWithText(EDIT_DATEPICKER).performClick()
        rule.onNodeWithText("OK").performClick()
        rule.onNode(timeTextField).performTextClearance()
        rule.onNode(timeTextField).performTextInput(EDIT_TIME)
        rule.onNode(injuredPartyTextField).performTextClearance()
        rule.onNode(injuredPartyTextField).performTextInput(EDIT_INJUREDPARTY)
        rule.onNode(injuryTextField).performTextClearance()
        rule.onNode(injuryTextField).performTextInput(EDIT_INJURY)
        rule.onNode(treatmentTextField).performTextClearance()
        rule.onNode(treatmentTextField).performTextInput(EDIT_TREATMENT)
        rule.onNode(adviceTextField).performTextClearance()
        rule.onNode(adviceTextField).performTextInput(EDIT_ADVICE)
        rule.onNode(confirmChangesButton).performClick()

        rule.onNode(listItemEdit).performClick()
        rule.onNode(editButton).performClick()

        rule.onNodeWithText(EDIT_LOCATION).assertExists()
        rule
            .onNodeWithText(EDIT_DATE).assertExists()
        rule
            .onNodeWithText(EDIT_TIME).assertExists()
        rule
            .onNodeWithText(EDIT_INJUREDPARTY).assertExists()
        rule
            .onNodeWithText(EDIT_INJURY).assertExists()
        rule
            .onNodeWithText(EDIT_TREATMENT).assertExists()
        rule
            .onNodeWithText(EDIT_ADVICE).assertExists()
        rule.onNode(deleteButton).performClick()
    }

    @Test
    fun `delete report`() {
        loginNotAdmin()
        enterValidReportForEdit()
        rule.onNode(listItem).performClick()
        rule.onNode(editButton).performClick()
        rule.onNode(deleteButton).performClick()
        rule.onNode(listItem).assertDoesNotExist()
    }
}