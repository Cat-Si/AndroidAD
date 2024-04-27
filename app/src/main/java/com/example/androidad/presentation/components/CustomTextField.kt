package com.example.androidad.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(hintText: String,
                    text: String,
                    isPasswordField: Boolean = false,
                    onValueChange: (String) -> Unit,
                    errorMessage: String,
                    errorPresent: Boolean,
                    singleLine: Boolean = true
){

    Surface(modifier = Modifier.padding(10.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            isError = errorPresent,
            label = {
                Text(hintText)
            },
            visualTransformation =  if (isPasswordField) PasswordVisualTransformation('*') else VisualTransformation.None,
            singleLine = singleLine
        )
        Text(
            modifier = Modifier.padding(10.dp),
            text =  if (errorPresent) "" else errorMessage,
            fontSize = 14.sp,
            color = Color.Red
        )
    }
}
