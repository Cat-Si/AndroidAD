package com.example.androidad.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ItemView(index: Int,
             item: String,
             selected: Boolean,
             onClick: (Int) -> Unit){
    androidx.compose.material.Text(
        text = item,
        modifier = Modifier
            .clickable {
                onClick.invoke(index)
            }
            .background(if (selected) MaterialTheme.colors.secondary else Color.Transparent)
            .fillMaxWidth()
            .padding(10.dp)
    )
}