package com.example.androidad.presentation.screens.add

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidad.R
import com.example.androidad.presentation.components.BottomNavBar
import com.example.androidad.presentation.components.CustomButton
import com.example.androidad.presentation.components.CustomTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddScreen(vm: AddViewModel = viewModel(factory = AddViewModel.Factory),
              modifier: Modifier = Modifier,
              navController: NavHostController,
              onClickToHome: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    )
    {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.add),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            Column {

                CustomTextField(
                    stringResource(R.string.first_name_hint),
                    text = vm.firstName,
                    onValueChange = { vm.firstName = it },
                    errorMessage = stringResource(R.string.first_name_error_message),
                    errorPresent = vm.firstNameIsValid()
                )

                CustomTextField(
                    stringResource(R.string.last_name_hint),
                    text = vm.surname,
                    onValueChange = { vm.surname = it },
                    errorMessage = stringResource(R.string.last_name_error_message),
                    errorPresent = vm.surnameIsValid()
                )

                CustomTextField(
                    stringResource(R.string.tel_no_hint),
                    text = vm.telNo,
                    onValueChange = { vm.telNo = it },
                    errorMessage = stringResource(R.string.tel_no_error_message),
                    errorPresent = vm.telNoIsValid()
                )
                CustomButton(
                    stringResource(R.string.add),
                    clickButton = {
                        vm.addContact()
                        keyboardController?.hide()
                        onClickToHome()
                    })
            }
        }
    }
}
