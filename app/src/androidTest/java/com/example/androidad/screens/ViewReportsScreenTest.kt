package com.example.androidad.screens

import androidx.compose.material.ListItem
import androidx.compose.ui.test.onNodeWithText
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
        rule.onNode(editButton).assertExists()
        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(addNavBarItem).assertExists()
        rule.onNode(exitNavBarItem).assertExists()
        rule.onNode(reportsNavBarItem).assertExists()
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
        goToEditScreen()
        rule.onNode(deleteButton).performClick()
    }

    @Test
    fun `can go to add screen`() {
        loginNotAdmin()
        goToAddScreen()
    }


}