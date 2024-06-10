package com.example.androidad.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.alpha

@Composable
fun CustomTextField(
    hintText: String,
    text: String,
    isPasswordField: Boolean = false,
    onValueChange: (String) -> Unit,
    errorMessage: String,
    errorPresent: Boolean,
    showError: Boolean,
    singleLine: Boolean = true,
    readOnly: Boolean = false,
    modifier: Modifier = Modifier,
) {

    Surface(modifier = Modifier.padding(10.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            isError = errorPresent && showError,
            label = {
                Text(hintText)
            },
            visualTransformation = if (isPasswordField) PasswordVisualTransformation('*') else VisualTransformation.None,
            singleLine = singleLine,
            readOnly = readOnly,
            modifier = Modifier.fillMaxWidth(.8f),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
                errorContainerColor = MaterialTheme.colorScheme.errorContainer,
                errorLabelColor = MaterialTheme.colorScheme.error,

                )
        )
        if (errorPresent && showError) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = errorMessage,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onErrorContainer
            )
        }
    }
}
