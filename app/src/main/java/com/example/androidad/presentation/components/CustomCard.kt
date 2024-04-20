package com.example.androidad.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CustomCard(location: String, building: String, room: String, window: String, mac: String) {
    Card(shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable{}
    ) {Column( modifier = Modifier.padding(15.dp))
    {
        Text("Location: $location", modifier = Modifier.padding(15.dp))
        Text("building: $building")
        Text("Room: $room")
        Text("Windows: $window")
        Text("Mac: $mac")
    }
     }
}
