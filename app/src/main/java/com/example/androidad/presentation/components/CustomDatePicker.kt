package com.example.androidad.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androidad.R
import com.example.androidad.presentation.utils.DateUtil


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerWithDialog(
    modifier: Modifier = Modifier,
//    text: String
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
    InputChip(
        modifier = Modifier,
        leadingIcon = {
            Icon(
                painterResource(id = R.drawable.calander),
                contentDescription = "Select Date",
                Modifier.size(InputChipDefaults.IconSize)) },
        selected = showDialog,
        onClick = { showDialog = true },
        label = { Text(dateToString) },
        shape =   OutlinedTextFieldDefaults.shape,
    )
}
        if (showDialog) {
            DatePickerDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(
                        onClick = { showDialog = false }
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

