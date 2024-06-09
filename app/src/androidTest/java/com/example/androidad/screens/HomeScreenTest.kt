package com.example.androidad.screens

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Test

class HomeScreenTest : ScreenTests() {


    @Before
    override fun setUp() {
        super.setUp()
    }

    @Test
    fun `check default state of home screen`() {
        loginAsAdmin()
        rule.onNode(homeScreenTitle).assertExists()
        rule.onNode(homeScreenSubHeading).assertExists()
        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(exitNavBarItem).assertExists()
        rule.onNode(reportsNavBarItem).assertExists()
    }


    @Test
    fun `can select a user and view their reports`() {
        loginAsAdmin()
        rule.onNode(adminTestUserItem).performClick()
        rule.mainClock.advanceTimeBy(500)
        Thread.sleep(1000)
        rule.onNode(viewReportsButton).performClick()
        rule.mainClock.advanceTimeBy(500)
        Thread.sleep(1000)
        rule.onNode(viewReportsTitle).assertExists()
    }

    @Test
    fun `can add a report to themselves`() {
        loginAsAdmin()
        rule.onNode(adminTestUserItem).performClick()
        rule.mainClock.advanceTimeBy(500)
        rule.onNode(viewReportsButton).performClick()
        rule.mainClock.advanceTimeBy(500)
        rule.onNode(addButton).performClick()
        enterValidReport()
        rule.onNode(adminListItem).performClick()
        rule.onNode(editButton).performClick()
        rule.onNode(deleteButton).performClick()
    }

    @Test
    fun `cannot add a report for someone else`() {
        loginAsAdmin()
        rule.onNode(userTestUserItem).performClick()
        rule.mainClock.advanceTimeBy(500)
        Thread.sleep(1000)
        rule.onNode(viewReportsButton).performClick()
        rule.onNode(addButton).assertDoesNotExist()

    }

}