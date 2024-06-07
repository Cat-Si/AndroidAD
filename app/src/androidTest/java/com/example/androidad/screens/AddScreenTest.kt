package com.example.androidad.screens

import androidx.compose.ui.test.assertTextEquals
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

    fun goToAddScreen() {
        loginNotAdmin()
        rule.onNode(addNavBarItem).performClick()
    }

    @Test
    fun `check default state of add screen`() {
        goToAddScreen()

        rule.onNode(addScreenTitle).assertExists()
        rule.onNode(firstaiderTextField).assertTextEquals(FIRSTAIDER)
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
        rule.onNode(homeNavBarItem).assertExists()
    }

    @Test
    fun `can submit new report`() {
        goToAddScreen()
        rule.onNode(locationTextField).performTextInput(LOCATION)
        rule.onNode(dateTextField).performClick()
        rule.onNodeWithText(DATE).performClick()
        rule.onNodeWithText("OK").performClick()
        rule.onNode(timeTextField).performTextInput(TIME)
        rule.onNode(injuredPartyTextField).performTextInput(INJUREDPARTY)
        rule.onNode(injuryTextField).performTextInput(INJURY)
        rule.onNode(treatmentTextField).performTextInput(TREATMENT)
        rule.onNode(adviceTextField).performTextInput(ADVICE)
        rule.onNode(submitButton).performClick()
        rule.onNode(viewReportsTitle).assertExists()
    }

    @Test
    fun `cannot submit a report with invalid data`() {
        goToAddScreen()
        rule.onNode(locationTextField).performTextInput("")
        rule.onNode(dateTextField).performClick()
        rule.onNodeWithText("OK").performClick()
        rule.onNode(timeTextField).performTextInput("")
        rule.onNode(injuredPartyTextField).performTextInput("")
        rule.onNode(injuryTextField).performTextInput("")
        rule.onNode(treatmentTextField).performTextInput("")
        rule.onNode(adviceTextField).performTextInput("")
        rule.onNode(submitButton).performClick()

        rule.onNode(locationTextField)
            .assertTextEquals(rule.activity.getString(R.string.location_error))
        rule.onNode(dateTextField)
            .assertTextEquals(rule.activity.getString(R.string.date_error))
        rule.onNode(timeTextField)
            .assertTextEquals(rule.activity.getString(R.string.time_error))
        rule.onNode(injuredPartyTextField)
            .assertTextEquals(rule.activity.getString(R.string.injured_party_error))
        rule.onNode(injuryTextField)
            .assertTextEquals(rule.activity.getString(R.string.injury))
        rule.onNode(treatmentTextField)
            .assertTextEquals(rule.activity.getString(R.string.treatment_error))
        rule.onNode(adviceTextField)
            .assertTextEquals(rule.activity.getString(R.string.advice_error))
    }
}