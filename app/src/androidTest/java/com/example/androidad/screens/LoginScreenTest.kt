package com.example.androidad.screens

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidad.R
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.DEFAULT)
@RunWith(AndroidJUnit4::class)
open class LoginScreenTest : ScreenTests() {

    @Before
    override fun setUp() {
        super.setUp()
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

//    @Test
//    fun `log in as admin`(email: String = "newadmin@email.com", password: String = "password") {
//        //rule.onNode(emailAddressTextField).printToLog("UI_TEST");
//        rule.onNode(emailAddressTextField).performTextInput(email)
//        rule.onNode(passwordTextField).performTextInput(password)
//        rule.onNode(submitButton).performClick()
//
//        Thread.sleep(1000)//pause or the following will fail - recommendation is an idle call back (not demonstrated here)
//    }

    @Test
    fun `Can admin user sign in and go to home page`() {
        loginAsAdmin()

    }

    @Test
    fun `Can non admin user sign in and go to reports page`() {
        loginNotAdmin()

    }

    /*@Test
    fun `attempt to sign in with invalid details`() {
    }*/

    @Test
    fun `move to the sign up page`() {
        rule.onNode(signUpButton).performClick()
        val pageTitle = hasText(rule.activity.getString(R.string.sign_up_screen_title))
        rule.onNode(pageTitle).assertExists()
    }

    /* @Test
     fun `forgotten password button is clicked`() {

     }*/


}