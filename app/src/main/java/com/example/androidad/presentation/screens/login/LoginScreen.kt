package com.example.androidad.presentation.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidad.presentation.screens.view_delete.HomeViewModel

@Composable
fun LoginScreen(vm: LoginViewModel = viewModel(factory = LoginViewModel.Factory), text:String,
                clickAction: () -> Unit,
                modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Button(
            onClick = clickAction,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(horizontal = 10.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = text, fontSize = 20.sp)
        }
    }
}