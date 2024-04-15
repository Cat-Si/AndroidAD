package com.example.navigation1.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(hintText: String,
              text: String,
              onNameChange: (String) -> Unit,
              errorMessage: String,
              errorPresent: Boolean){


    Surface(modifier = Modifier.padding(10.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = onNameChange,
            isError = errorPresent,
            singleLine = true,
            label = {
                Text(hintText)
            }
        )
        Text(
            modifier = Modifier.padding(10.dp).testTag("Test".plus(hintText)),
            text =  if (errorPresent) "" else errorMessage,
            fontSize = 14.sp,
            color = Color.Red,
        )
    }
}