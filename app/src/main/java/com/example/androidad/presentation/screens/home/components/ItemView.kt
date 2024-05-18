package com.example.androidad.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androidad.data.report.Report

@Composable
fun ItemView(
    index: Int,
    report: Report,
    selected: Boolean,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onClick.invoke(index) }
            .background(if (selected) MaterialTheme.colorScheme.background else Color.Transparent)
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primaryContainer,
        ),
        //elevation =
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = "Date: ${report.date ?: "N/A"}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Location: ${report.location ?: "N/A"}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Injury: ${report.injury ?: "N/A"}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}