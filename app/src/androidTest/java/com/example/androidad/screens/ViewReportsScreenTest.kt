package com.example.androidad.screens

import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Test

class ViewReportsScreenTest : ScreenTests() {
    @Before
    override fun setUp() {
        super.setUp()
    }

    @Test
    fun `check default state of view reports screen`() {
        loginNotAdmin()
        rule.onNode(viewReportsTitle).assertExists()
        rule.onNode(userItem).assertExists()
        rule.onNode(editButton).assertExists()
        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(addNavBarItem).assertExists()
        rule.onNode(exitNavBarItem).assertExists()
        rule.onNode(homeNavBarItem).assertExists()
    }

    @Test
    fun `cannot edit no report`() {
        loginNotAdmin()
        rule.onNode(editButton).performClick()
        rule.onNode(viewReportsTitle).assertExists()
    }

    @Test
    fun `can go to edit screen`() {
        loginNotAdmin()
        enter_a_valid_report()
        rule.onNode(listItem).performClick()
        rule.onNode(editButton).performClick()
        rule.onNode(editScreenTitle).assertExists()
    }


}