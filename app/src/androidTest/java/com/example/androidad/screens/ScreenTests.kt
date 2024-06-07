package com.example.androidad.screens

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasNoClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.androidad.R
import com.example.androidad.core.MainActivity
import com.example.androidad.presentation.navigation.NavScreen
import org.junit.Before
import org.junit.Rule

class ScreenTests {
    abstract class ScreenTests {
        @get:Rule
        var rule = createAndroidComposeRule<MainActivity>()

        //Nav bar items
        val bottomNavBar = hasContentDescription("bottom_nav")
        val exitNavBarItem = hasText(NavScreen.Exit.route)
        val homeNavBarItem = hasText(NavScreen.Home.route) and hasAnySibling(exitNavBarItem)
        val addNavBarItem = hasText(NavScreen.Add.route) and hasAnySibling(exitNavBarItem)

        lateinit var submitButton: SemanticsMatcher

        //Data for add screen
        val FIRSTAIDER = "first1"
        val LOCATION = "location"
        val DATE = "20 January 2024"
        val TIME = "noon"
        val INJUREDPARTY = "Person injured"
        val INJURY = "Injury"
        val TREATMENT = "treatment"
        val ADVICE = "advice"

        lateinit var firstaiderTextField: SemanticsMatcher
        lateinit var locationTextField: SemanticsMatcher
        lateinit var dateTextField: SemanticsMatcher
        lateinit var timeTextField: SemanticsMatcher
        lateinit var injuredPartyTextField: SemanticsMatcher
        lateinit var injuryTextField: SemanticsMatcher
        lateinit var treatmentTextField: SemanticsMatcher
        lateinit var adviceTextField: SemanticsMatcher
        lateinit var addScreenText: SemanticsMatcher
        lateinit var addButton: SemanticsMatcher

        //For home screen
        val listItem =
            hasText("$FIRSTAIDER $LOCATION $DATE $TIME $INJUREDPARTY $INJURY $TREATMENT $ADVICE")
        lateinit var homeScreenText: SemanticsMatcher
        lateinit var forgotPasswordButton: SemanticsMatcher
        lateinit var signUpButton: SemanticsMatcher
        lateinit var deleteButton: SemanticsMatcher

        //For login + sign up screen
        lateinit var emailAddressTextField: SemanticsMatcher
        lateinit var passwordTextField: SemanticsMatcher
        lateinit var firstNameTextField: SemanticsMatcher
        lateinit var lastNameTextField: SemanticsMatcher
        var adminSwitch = hasContentDescription("admin switch")

        lateinit var backButton: SemanticsMatcher

        //For edit screen
        lateinit var editScreenText: SemanticsMatcher

        @Before
        open fun setUp() {
            val BUTTON_POSTFIX = " button"
            forgotPasswordButton =
                hasContentDescription(rule.activity.getString(R.string.forgot_password) + BUTTON_POSTFIX)
            submitButton =
                hasContentDescription(rule.activity.getString(R.string.submit_button) + BUTTON_POSTFIX)
            signUpButton =
                hasContentDescription(rule.activity.getString(R.string.sign_up_button) + BUTTON_POSTFIX)
            backButton =
                hasContentDescription(rule.activity.getString(R.string.back_button) + BUTTON_POSTFIX)
            deleteButton =
                hasContentDescription(rule.activity.getString(R.string.delete) + BUTTON_POSTFIX)
            addButton =
                hasContentDescription(rule.activity.getString(R.string.add) + BUTTON_POSTFIX)

//            emailAddressTextField = hasContentDescription(rule.activity.getString(R.string.email))
//            passwordTextField = hasContentDescription(rule.activity.getString(R.string.password))
            emailAddressTextField =
                hasText(rule.activity.getString(R.string.email))
            passwordTextField =
                hasText(rule.activity.getString(R.string.password))
            firstNameTextField =
                hasText(rule.activity.getString(R.string.first_name_hint))
            lastNameTextField =
                hasText(rule.activity.getString(R.string.last_name_hint))

            firstaiderTextField =
                hasContentDescription(rule.activity.getString(R.string.firstAider))
            locationTextField = hasContentDescription(rule.activity.getString(R.string.location))
            dateTextField = hasContentDescription(rule.activity.getString(R.string.date))
            timeTextField = hasContentDescription(rule.activity.getString(R.string.time))
            injuredPartyTextField =
                hasContentDescription(rule.activity.getString(R.string.injured_party))
            injuryTextField = hasContentDescription(rule.activity.getString(R.string.injury))
            treatmentTextField = hasContentDescription(rule.activity.getString(R.string.treatment))
            adviceTextField = hasContentDescription(rule.activity.getString(R.string.advice))


            homeScreenText = hasText(rule.activity.getString(R.string.home))
            addScreenText = hasText(rule.activity.getString(R.string.add)) and hasNoClickAction()
            editScreenText = hasText(rule.activity.getString(R.string.edit))
        }

        //Use for valid and invalid sign ins - use default values for generic log in
        fun `log in not admin`(email: String = "newuser@email.com", password: String = "password") {
            //rule.onNode(emailAddressTextField).printToLog("UI_TEST");
            rule.onNode(emailAddressTextField).performTextInput(email)
            rule.onNode(passwordTextField).performTextInput(password)
            rule.onNode(submitButton).performClick()

            Thread.sleep(1000)//pause or the following will fail - recommendation is an idle call back (not demonstrated here)
        }

        fun `log in as admin`(email: String = "newadmin@email.com", password: String = "password") {
            //rule.onNode(emailAddressTextField).printToLog("UI_TEST");
            rule.onNode(emailAddressTextField).performTextInput(email)
            rule.onNode(passwordTextField).performTextInput(password)
            rule.onNode(submitButton).performClick()

            Thread.sleep(1000)//pause or the following will fail - recommendation is an idle call back (not demonstrated here)
        }

        //Used by add screen + home screen creating a user before editing
        fun `enter_a_valid_report`() {
            rule.onNode(firstaiderTextField).performTextInput(FIRSTAIDER)
            rule.onNode(locationTextField).performTextInput(LOCATION)
            rule.onNode(dateTextField).performTextInput(DATE)
            rule.onNode(timeTextField).performTextInput(TIME)
            rule.onNode(injuredPartyTextField).performTextInput(INJUREDPARTY)
            rule.onNode(injuryTextField).performTextInput(INJURY)
            rule.onNode(treatmentTextField).performTextInput(TREATMENT)
            rule.onNode(adviceTextField).performTextInput(ADVICE)

            rule.onNode(addButton).performClick()
        }
    }
}