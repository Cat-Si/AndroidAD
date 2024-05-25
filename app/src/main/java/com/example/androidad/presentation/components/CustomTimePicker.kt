package com.example.androidad.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerWithDialog(
    modifier: Modifier = Modifier,
    onTimeSelected: (String) -> Unit,
    text: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    errorMessage: String,
    errorPresent: Boolean,
    showError: Boolean,
) {
    val timeState = rememberTimePickerState()

    var showDialog by remember { mutableStateOf(false) }
    Surface(modifier = Modifier.padding(10.dp)) {
        ReadOnlyTextField(
            text = text,
            onValueChange = onValueChange,
            label = label,
            onClick = { showDialog = true },
            errorMessage = errorMessage,
            errorPresent = errorPresent,
            showError = showError
        )
    }
    if (showDialog) {

        TimeInput(
            state = timeState,
            modifier = Modifier.padding(16.dp)
        )
    }
}