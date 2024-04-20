package com.example.androidad.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun CustomDropDownMenu( menuItem: List<String> ) {
//    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedText = "please select"
    //var selectedIndex by remember { mutableStateOf(0) }


    Box(
        modifier = Modifier.fillMaxWidth()
            .wrapContentSize(Alignment.TopStart)
    ) {
        TextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth().clickable(onClick = { expanded = true }).background(
                Color.Gray)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            menuItem.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        selectedText = item.toString()
                        expanded = false
        })
    }
}
    }
}

