package com.example.navigation1.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(text: String, clickButton: () -> Unit) {
    Button(
        onClick = clickButton,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(horizontal = 10.dp).testTag("Test".plus(text))
    ) {
        Text(text = text, fontSize = 20.sp)
    }
}
