package com.example.androidad.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

@Composable
fun ReadOnlyTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    readOnly: Boolean = false,
    onClick: () -> Unit,
    label: @Composable () -> Unit
){

    Box  {
        OutlinedTextField(
            modifier = modifier,
            value = text,
            onValueChange = onValueChange,
            label = label,
            readOnly = readOnly
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable(onClick = onClick),
        )
    }
}