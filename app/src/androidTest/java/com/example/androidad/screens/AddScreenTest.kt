package com.example.androidad.screens

import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.androidad.R
import org.junit.Before
import org.junit.Test

class AddScreenTest : ScreenTests() {
    @Before
    override fun setUp() {
        super.setUp()
    }


    @Test
    fun `check default state of add screen`() {
        loginNotAdmin()
        goToAddScreen()

        rule.onNode(addScreenTitle).assertExists()
        rule.onNode(firstaiderTextField).assertExists()
        rule.onNode(locationTextField).assertExists()
        rule.onNode(dateTextField).assertExists()
        rule.onNode(timeTextField).assertExists()
        rule.onNode(injuredPartyTextField).assertExists()
        rule.onNode(injuryTextField).assertExists()
        rule.onNode(treatmentTextField).assertExists()
        rule.onNode(adviceTextField).assertExists()

        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(addNavBarItem).assertExists()
        rule.onNode(exitNavBarItem).assertExists()
        rule.onNode(reportsNavBarItem).assertExists()
    }

    @Test
    fun `can submit new report`() {
        loginNotAdmin()

        goToAddScreen()
        enterValidReport()
        rule.onNode(viewReportsTitle)
            .assertExists() //if it doesnt submit then it wont go back to the reports screen, further new report checks in edit screen
    }

    @Test
    fun `cannot submit a report with invalid data`() {
        loginNotAdmin()

        goToAddScreen()
        rule.onNode(locationTextField).performTextInput("")
        rule.onNode(dateTextField).performClick()
        rule.onNodeWithText("OK").performClick()
        rule.onNode(timeTextField).performTextInput("")
        rule.onNode(injuredPartyTextField).performTextInput("")
        rule.onNode(injuryTextField).performTextInput("")
        rule.onNode(treatmentTextField).performTextInput("")
        rule.onNode(adviceTextField).performTextInput("")
        rule.onNode(addButton).performClick()

        rule.onNodeWithText(rule.activity.getString(R.string.location_error)).assertExists()

        rule
            .onNodeWithText(rule.activity.getString(R.string.time_error)).assertExists()
        rule
            .onNodeWithText(rule.activity.getString(R.string.injured_party_error)).assertExists()
        rule
            .onNodeWithText(rule.activity.getString(R.string.injury)).assertExists()
        rule
            .onNodeWithText(rule.activity.getString(R.string.treatment_error)).assertExists()
        rule
            .onNodeWithText(rule.activity.getString(R.string.advice_error)).assertExists()
    }
}