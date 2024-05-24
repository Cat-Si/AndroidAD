package com.example.androidad.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReadOnlyTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    errorMessage: String,
    errorPresent: Boolean,
    showError: Boolean,
    readOnly: Boolean = false,
    onClick: () -> Unit,
    label: @Composable () -> Unit
){

    Box  {
        OutlinedTextField(
            modifier = modifier,
            value = text,
            onValueChange = onValueChange,
            isError = errorPresent && showError,
            label = label,
            readOnly = readOnly
        )
        if(errorPresent && showError)
            Text(
                modifier = Modifier.padding(10.dp),
                text =  errorMessage,
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