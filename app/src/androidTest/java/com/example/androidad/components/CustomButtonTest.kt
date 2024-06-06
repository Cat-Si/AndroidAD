package com.example.androidad.components

import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import com.example.androidad.presentation.components.CustomButton
import org.junit.Assert.*
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.DEFAULT)
class CustomButtonTest {

    private val TEXT_DISPLAY = "button text"
    private var state: Boolean = false

    private val button = hasText(TEXT_DISPLAY) and hasClickAction()

    @get:Rule
    var rule = createComposeRule()

    @Before
    fun setUp() {
        rule.setContent {
            CustomButton(TEXT_DISPLAY, clickButton = { state = true })
        }
    }

    @Test
    fun `check if displays text and executes function passed to it when clicked`() {
        rule.onNode(button).assertExists()
        rule.onNode(button).performClick()
        assertTrue(state)
    }

}