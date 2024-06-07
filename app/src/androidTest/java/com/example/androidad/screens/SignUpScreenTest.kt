package com.example.androidad.screens

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.matcher.ViewMatchers.hasContentDescription
import com.example.androidad.R
import com.example.androidad.core.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

open class SignUpScreenTest : ScreenTests() {


    @Before
    override fun setUp() {
        super.setUp()
    }

    @Test
    fun `check default state of the sign up screen`() {
        rule.onNode(signUpButton).performClick()
//on sign up page
        val pageTitle =
            hasText(rule.activity.getString(R.string.sign_up_screen_title))
        rule.onNode(pageTitle).assertExists()
        rule.onNode(bottomNavBar).assertDoesNotExist()
        rule.onNode(emailAddressTextField).assertExists()
        rule.onNode(passwordTextField).assertExists()
        rule.onNode(submitButton).assertExists()
        rule.onNode(adminSwitch).assertExists()
    }

    @Test
    fun `enter valid admin sign up details`() {
        rule.onNode(signUpButton).performClick()
        rule.onNode(emailAddressTextField).performTextInput(VALID_ADMIN_EMAIL)
        rule.onNode(passwordTextField).performTextInput(VALID_PASSWORD)
        rule.onNode(firstNameTextField).performTextInput(VALID_FIRST_NAME)
        rule.onNode(lastNameTextField).performTextInput(VALID_LAST_NAME)
        rule.onNode(adminSwitch).performClick()
        rule.onNode(submitButton).performClick()
    }

    @Test
    fun `enter valid non admin sign up details`() {
        rule.onNode(signUpButton).performClick()
        rule.onNode(emailAddressTextField).performTextInput(VALID_USER_EMAIL)
        rule.onNode(passwordTextField).performTextInput(VALID_PASSWORD)
        rule.onNode(firstNameTextField).performTextInput(VALID_FIRST_NAME)
        rule.onNode(lastNameTextField).performTextInput(VALID_LAST_NAME)
        rule.onNode(submitButton).performClick()
    }

    /*@Test
    fun `enter invalid sign up details`() {
        // USES TOAST CANNOT TEST

    }*/
}