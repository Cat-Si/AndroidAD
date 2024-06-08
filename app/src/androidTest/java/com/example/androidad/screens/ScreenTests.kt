package com.example.androidad.screens

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasClickAction
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

open abstract class ScreenTests {

    @get:Rule
    var rule = createAndroidComposeRule<MainActivity>()

    //Valid admin user details
    val VALID_ADMIN_EMAIL = "newadmin@email.com"
    val VALID_PASSWORD = "password"
    val VALID_FIRST_NAME = "First"
    val VALID_LAST_NAME = "Last"

    //valid non admin user details
    val VALID_USER_EMAIL = "newuser@email.com"

    //Nav bar items
    val bottomNavBar = hasContentDescription("bottom nav")
    val exitNavBarItem = hasText(NavScreen.Exit.route)
    val homeNavBarItem = hasText(NavScreen.ViewReports.route) and hasAnySibling(exitNavBarItem)
    val addNavBarItem = hasText(NavScreen.Add.route) and hasAnySibling(exitNavBarItem)

    lateinit var submitButton: SemanticsMatcher

    //Data for add screen
    val FIRSTAIDER = "first1"
    val LOCATION = "location"
    val DATE = "Thursday, June 13, 2024"
    val TIME = "between 12 and 1pm"
    val INJUREDPARTY = "Person injured"
    val INJURY = "Injury"
    val TREATMENT = "treatment"
    val ADVICE = "advice"

    //Data for edit screen
    val EDIT_LOCATION = "EDIT LOCATION"
    val EDIT_DATE = "Wednesday, 12 June, 2024"
    val EDIT_TIME = "Edit TIme"
    val EDIT_INJUREDPARTY = "Edit Person"
    val EDIT_INJURY = "Edit Injury"
    val EDIT_TREATMENT = "Edit Treatment"
    val EDIT_ADVICE = "Edit Advice"

    //Buttons
    lateinit var addButton: SemanticsMatcher
    lateinit var viewReportsButton: SemanticsMatcher
    lateinit var backButton: SemanticsMatcher
    lateinit var deleteButton: SemanticsMatcher
    lateinit var signUpButton: SemanticsMatcher
    lateinit var forgotPasswordButton: SemanticsMatcher
    lateinit var editButton: SemanticsMatcher
    lateinit var confirmChangesButton: SemanticsMatcher


    lateinit var firstaiderTextField: SemanticsMatcher
    lateinit var locationTextField: SemanticsMatcher
    lateinit var dateTextField: SemanticsMatcher
    lateinit var timeTextField: SemanticsMatcher
    lateinit var injuredPartyTextField: SemanticsMatcher
    lateinit var injuryTextField: SemanticsMatcher
    lateinit var treatmentTextField: SemanticsMatcher
    lateinit var adviceTextField: SemanticsMatcher
    lateinit var addScreenText: SemanticsMatcher

    //For login + sign up screen
    lateinit var emailAddressTextField: SemanticsMatcher
    lateinit var passwordTextField: SemanticsMatcher
    lateinit var firstNameTextField: SemanticsMatcher
    lateinit var lastNameTextField: SemanticsMatcher
    lateinit var adminSwitch: SemanticsMatcher

//    val PATTERN = Pattern.compile((0-9){6}))
    //    val regexUserName = Regex.((0 - 9){ 6 })

    //For home screen
    val userItem = hasText("$VALID_FIRST_NAME $VALID_LAST_NAME")

    //For reports view screem
    val listItem =
        hasText("First Aider: $FIRSTAIDER \n Date: $DATE \n Location: $LOCATION \n Injury: $INJURY")

    //For edit screen
    val editListItem =
        hasText("First Aider: $FIRSTAIDER \n Date: $EDIT_DATE \n Location: $EDIT_LOCATION \n Injury: $EDIT_INJURY")

    //Screen Titles
    lateinit var homeScreenTitle: SemanticsMatcher
    lateinit var homeScreenSubHeading: SemanticsMatcher
    lateinit var editScreenTitle: SemanticsMatcher
    lateinit var addScreenTitle: SemanticsMatcher
    lateinit var viewReportsTitle: SemanticsMatcher
    lateinit var viewReportsSubHeading: SemanticsMatcher


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
        viewReportsButton =
            hasContentDescription(rule.activity.getString(R.string.viewReports) + BUTTON_POSTFIX) and hasClickAction()
        editButton =
            hasContentDescription(rule.activity.getString(R.string.edit) + BUTTON_POSTFIX)
        confirmChangesButton =
            hasContentDescription(rule.activity.getString(R.string.confirmChanges))
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
        adminSwitch = hasContentDescription("admin switch")

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


        homeScreenTitle = hasText(rule.activity.getString(R.string.home_screen_title))
        homeScreenSubHeading = hasText(rule.activity.getString(R.string.selectUser))
        addScreenTitle =
            hasText(rule.activity.getString(R.string.add_screen_title)) and hasNoClickAction()
        editScreenTitle = hasText(rule.activity.getString(R.string.edit_screen_title))
        viewReportsTitle = hasText(rule.activity.getString(R.string.view_reports_screen_title))
    }

    //Use for valid and invalid sign ins - use default values for generic log in
    fun loginNotAdmin(email: String = "newuser@email.com", password: String = "password") {
        //rule.onNode(emailAddressTextField).printToLog("UI_TEST");
        rule.onNode(emailAddressTextField).performTextInput(email)
        rule.onNode(passwordTextField).performTextInput(password)
        rule.onNode(submitButton).performClick()

        Thread.sleep(1000)//pause or the following will fail - recommendation is an idle call back (not demonstrated here)
    }

    fun loginAsAdmin(email: String = "newadmin@email.com", password: String = "password") {
        //rule.onNode(emailAddressTextField).printToLog("UI_TEST");
        rule.onNode(emailAddressTextField).performTextInput(email)
        rule.onNode(passwordTextField).performTextInput(password)
        rule.onNode(submitButton).performClick()

        Thread.sleep(1000)//pause or the following will fail - recommendation is an idle call back (not demonstrated here)
    }

    //Used by add screen + home screen creating a user before editing
    fun `enter_a_valid_report`() {
        rule.onNode(addNavBarItem).performClick()

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

    fun `log out`() {
        rule.onNode(exitNavBarItem).performClick()
    }

    fun `click reports`() {
        rule.onNode(homeNavBarItem).performClick()
    }
}
