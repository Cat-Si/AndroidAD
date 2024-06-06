package com.example.androidad.screens

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import com.example.androidad.R
import com.example.androidad.core.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {
    @get:Rule
    var rule = createAndroidComposeRule<MainActivity>()

    private lateinit var emailAddressTextField: SemanticsMatcher
    private lateinit var passwordTextField: SemanticsMatcher
    private lateinit var submitButton: SemanticsMatcher
    private lateinit var forgotPasswordButton: SemanticsMatcher
    private lateinit var signUpButton: SemanticsMatcher

    @Before
    fun setUp() {
        emailAddressTextField =
            hasText(rule.activity.getString(R.string.email))
        passwordTextField =
            hasText(rule.activity.getString(R.string.password))
        submitButton =
            hasText(rule.activity.getString(R.string.submit_button)) and hasClickAction()
        forgotPasswordButton =
            hasText(rule.activity.getString(R.string.forgot_password)) and hasClickAction()
        signUpButton =
            hasText(rule.activity.getString(R.string.sign_up_button)) and hasClickAction()
    }
    /*
    * email: CustomText
    * password: CustomText
    * title: text
    * submit: customButton
    * sign up: customButton
    * forgot password: customButton*/

    @Test
    fun `Check state of login page`() {
        rule.onNode(submitButton).assertExists()
        rule.onNode(forgotPasswordButton).assertExists()
        rule.onNode(signUpButton).assertExists()
        rule.onNode(emailAddressTextField).assertExists()
        rule.onNode(passwordTextField).assertExists()
    }

    @Test
    fun `Can admin user sign in and go to home page`() {
    }

    @Test
    fun `Can non admin user sign in and go to reports page`() {
    }

    @Test
    fun `attempt to sign in with invalid details`() {
    }

    @Test
    fun `move to the sign up page`() {
        rule.onNode(signUpButton).performClick()
        val pageTitle = hasText(rule.activity.getString(R.string.sign_up_screen_title))
        rule.onNode(pageTitle).assertExists()
    }


}