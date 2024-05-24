package com.example.androidad.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidad.presentation.utils.DateUtil


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerWithDialog(
    modifier: Modifier = Modifier,
    onDateSelected: (String) -> Unit,
    text: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    errorMessage: String,
    errorPresent: Boolean,
    showError: Boolean,

){
    val dateState = rememberDatePickerState()
    val millisToLocalDate = dateState.selectedDateMillis?.let {
        DateUtil().convertMillisToLocalDate(it)
    }
    val dateToString = millisToLocalDate?.let {
        DateUtil().dateToString(millisToLocalDate)
    } ?: "Choose Date"
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
            DatePickerDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            dateToString?.let { onDateSelected(it) }
                        }
                    ) {
                        Text(text = "OK")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text(text = "Cancel")
                    }
                }
            ) {
                DatePicker(
                    state = dateState,
                    showModeToggle = true
                )
            }
        }

    }

