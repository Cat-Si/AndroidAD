package com.example.androidad.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReadOnlyTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    errorMessage: String,
    errorPresent: Boolean,
    showError: Boolean,

    ) {

    Box {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(.8f),
            value = text,
            onValueChange = onValueChange,
            label = label,
            isError = errorPresent && showError,
            readOnly = true
        )
        if (errorPresent && showError)
            Text(
                modifier = Modifier.padding(10.dp),
                text = errorMessage,
                fontSize = 14.sp,
                color = Color.Red
            )

        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable(onClick = onClick),
        )
    }
}