package com.example.androidad.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ItemView(
    index: Int,
    item: String,
    selected: Boolean,
    onClick: (Int) -> Unit,

    ) {
    Text(
        text = item,
        modifier = Modifier
            .clickable {
                onClick.invoke(index)
            }
            .background(if (selected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
            .padding(10.dp),
        color = if (selected) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onPrimaryContainer

    )
}