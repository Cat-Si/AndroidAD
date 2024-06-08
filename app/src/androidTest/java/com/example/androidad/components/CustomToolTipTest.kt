package com.example.androidad.components

import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.androidad.presentation.components.CustomToolTip
import org.junit.Rule
import org.junit.Test

class CustomToolTipTest {

    private val TOOLTIP_TEXT = "Tooltip Text"
    private val ICON_DESCRIPTION = "Localized Description"
    private val ANCHOR_ELEMENT = @Composable { BasicText(text = "Anchor Element") }

    @get:Rule
    var rule = createComposeRule()

    @Test
    fun `tooltip displays on click`() {
        rule.setContent {
            CustomToolTip(
                elementToAnchor = { ANCHOR_ELEMENT },
                text = TOOLTIP_TEXT
            )
        }
        rule.onNodeWithText(TOOLTIP_TEXT).assertDoesNotExist()
        rule.onNodeWithContentDescription(ICON_DESCRIPTION).performClick()
        rule.onNodeWithText(TOOLTIP_TEXT).assertIsDisplayed()
    }

    @Test
    fun `Tooltip is hidden after click`() {
        rule.setContent {
            CustomToolTip(
                elementToAnchor = { ANCHOR_ELEMENT },
                text = TOOLTIP_TEXT
            )
        }
        rule.onNodeWithContentDescription(ICON_DESCRIPTION).performClick()
        rule.onNodeWithText(TOOLTIP_TEXT).assertIsDisplayed()
        rule.mainClock.advanceTimeBy(9000)
        rule.onNodeWithText(TOOLTIP_TEXT).assertDoesNotExist()
    }

}
