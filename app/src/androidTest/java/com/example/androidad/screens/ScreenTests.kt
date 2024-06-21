package com.example.androidad.screens

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasNoClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
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
    val VALID_ADMIN_EMAIL = "catherinesheendevelopment@gmail.com"
    val VALID_PASSWORD = "Password"
    val VALID_ADMIN_FIRST_NAME = "Catherine"
    val VALID_ADMIN_LAST_NAME = "Sheen"

    //valid non admin user details
    val VALID_USER_EMAIL = "kasiasheen@outlook.com"
    val VALID_USER_FIRST_NAME = "Kasia"
    val VALID_USER_LAST_NAME = "Sheen"

    //Nav bar items
    val bottomNavBar = hasContentDescription("bottom nav")
    val exitNavBarItem = hasText(NavScreen.Exit.route)
    val reportsNavBarItem = hasText(NavScreen.ViewReports.label) and hasAnySibling(exitNavBarItem)
    val addNavBarItem = hasText(NavScreen.Add.label) and hasAnySibling(exitNavBarItem)
    val homeNavBarItem = hasText(NavScreen.Home.label) and hasAnySibling(exitNavBarItem)


    lateinit var submitButton: SemanticsMatcher

    //Data for add screen
    val FIRSTAIDER = "Kasia Sheen"
    val LOCATION = "location"
    val DATEPICKER = "Thursday, June 13, 2024"
    val DATE = "Thursday, 13 June, 2024" //date structure is different for selector and reading back
    val TIME = "between 12 and 1pm"
    val INJUREDPARTY = "Person injured"
    val INJURY = "Injury"
    val TREATMENT = "treatment"
    val ADVICE = "advice"


    //Data for edit screen
    val LOCATIONEDIT = "TEST"
    val EDIT_LOCATION = "EDIT LOCATION"
    val EDIT_DATEPICKER = "Wednesday, June 12, 2024" //date format for datepicker selection
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
    val adminTestUserItem = hasText("Catherine Sheen (sheenc6030)")
    val userTestUserItem = hasText("Kasia Sheen (sheenk3610)")

    //For reports view screem
//    val listItem =
//        hasText(
//            "First Aider: $FIRSTAIDER Date: $DATE Location: $LOCATIONEDIT Injury: $INJURY",
//            ignoreCase = true
//        )
    lateinit var listItem: SemanticsMatcher
    lateinit var listItemEdit: SemanticsMatcher
    lateinit var adminListItem: SemanticsMatcher

    //For edit screen
//    var editListItem =
//        hasText("First Aider: $FIRSTAIDER \n Date: $EDIT_DATEPICKER \n Location: $EDIT_LOCATION \n Injury: $EDIT_INJURY")

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
            hasContentDescription(rule.activity.getString(R.string.confirmChanges) + BUTTON_POSTFIX)
//            emailAddressTextField = hasContentDescription(rule.activity.getString(R.string.email))
//            passwordTextField = hasContentDescription(rule.activity.getString(R.string.password))
//        listItem = hasContentDescription("Kasia Sheen Thursday, 13 June, 2024 TEST Injury")
        listItem = hasContentDescription("$FIRSTAIDER $DATE $LOCATIONEDIT $INJURY")
        listItemEdit = hasContentDescription("$FIRSTAIDER $EDIT_DATE $EDIT_LOCATION $EDIT_INJURY")
        adminListItem = hasContentDescription("Catherine Sheen $DATE $LOCATION $INJURY")
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
            hasText(rule.activity.getString(R.string.firstAider))
        locationTextField = hasText(rule.activity.getString(R.string.location))
        dateTextField = hasText(rule.activity.getString(R.string.date))
        timeTextField = hasText(rule.activity.getString(R.string.time))
        injuredPartyTextField =
            hasText(rule.activity.getString(R.string.injured_party))
        injuryTextField = hasText(rule.activity.getString(R.string.injury))
        treatmentTextField = hasText(rule.activity.getString(R.string.treatment))
        adviceTextField = hasText(rule.activity.getString(R.string.advice))


        homeScreenTitle = hasText(rule.activity.getString(R.string.home_screen_title))
        homeScreenSubHeading = hasText(rule.activity.getString(R.string.selectUser))
        addScreenTitle =
            hasText(rule.activity.getString(R.string.add_screen_title)) and hasNoClickAction()
        editScreenTitle = hasText(rule.activity.getString(R.string.edit_screen_title))
        viewReportsTitle = hasText(rule.activity.getString(R.string.view_reports_screen_title))
    }

    //Use for valid and invalid sign ins - use default values for generic log in
    fun loginNotAdmin() {
        //rule.onNode(emailAddressTextField).printToLog("UI_TEST");
        rule.onNode(emailAddressTextField).performTextInput(VALID_USER_EMAIL)
        rule.onNode(passwordTextField).performTextInput(VALID_PASSWORD)
        rule.onNode(submitButton).performClick()

        Thread.sleep(1000)//pause or the following will fail - recommendation is an idle call back (not demonstrated here)
        rule.mainClock.advanceTimeBy(500)
        Thread.sleep(1000)

        rule.onNode(viewReportsTitle).assertExists()

    }

    fun loginAsAdmin() {
        //rule.onNode(emailAddressTextField).printToLog("UI_TEST");
        rule.onNode(emailAddressTextField).performTextInput(VALID_ADMIN_EMAIL)
        rule.onNode(passwordTextField).performTextInput(VALID_PASSWORD)
        rule.onNode(submitButton).performClick()

        Thread.sleep(1000)//pause or the following will fail - recommendation is an idle call back (not demonstrated here)
        rule.mainClock.advanceTimeBy(500)
        Thread.sleep(1000)

        rule.onNode(homeScreenTitle).assertExists()

    }

    //Used by add screen + home screen creating a user before editing
    fun enterValidReport() {

        rule.onNode(locationTextField).performTextInput(LOCATION)
        rule.onNode(dateTextField).performClick()
        rule.onNodeWithText(DATEPICKER).performClick()
        rule.onNodeWithText("OK").performClick()
        rule.onNode(timeTextField).performTextInput(TIME)
        rule.onNode(injuredPartyTextField).performTextInput(INJUREDPARTY)
        rule.onNode(injuryTextField).performTextInput(INJURY)
        rule.onNode(treatmentTextField).performTextInput(TREATMENT)
        rule.onNode(adviceTextField).performTextInput(ADVICE)
        rule.onNode(addButton).performClick()
    }

    fun enterValidReportForEdit() {
        rule.onNode(addNavBarItem).performClick()

        rule.onNode(locationTextField).performTextInput(LOCATIONEDIT)
        rule.onNode(dateTextField).performClick()
        rule.onNodeWithText(DATEPICKER).performClick()
        rule.onNodeWithText("OK").performClick()
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
        rule.onNode(reportsNavBarItem).performClick()
    }

    fun goToEditScreen() {
        enterValidReportForEdit()
        rule.onNode(listItem)
            .performClick()
        rule.onNode(editButton).performClick()
        rule.onNode(editScreenTitle).assertExists()
    }

    fun goToAddScreen() {
        rule.onNode(addNavBarItem).performClick()
        rule.onNode(addScreenTitle).assertExists()
    }
}
