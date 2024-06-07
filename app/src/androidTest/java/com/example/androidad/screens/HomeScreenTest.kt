package com.example.androidad.screens

import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Test

class HomeScreenTest : ScreenTests() {

    val DISPLAYNAME = "First Admin"

    @Before
    override fun setUp() {
        super.setUp()
    }

    @Test
    fun `check default state of home screen`() {
        `log in as admin`()
        rule.onNode(homeScreenTitle).assertExists()
        rule.onNode(homeScreenSubHeading).assertExists()
        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(addNavBarItem).assertExists()
        rule.onNode(exitNavBarItem).assertExists()
        rule.onNode(homeNavBarItem).assertExists()
    }

    @Test
    fun `go to the add screen`() {
        `log in as admin`()
        rule.onNode(addNavBarItem).performClick()
    }

    @Test
    fun `can select a user and view their reports`() {
        `log in as admin`()
        rule.onNode(userItem).performClick()
        rule.onNode(viewReportsButton).performClick()
        rule.onNode(viewReportsTitle).assertExists()
    }

}