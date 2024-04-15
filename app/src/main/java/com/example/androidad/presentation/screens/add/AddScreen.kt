package com.example.bottomnav1.presentation.screens.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
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
import com.example.androidad.R
import com.example.navigation1.presentation.components.CustomButton
import com.example.navigation1.presentation.components.CustomTextField

@Composable
fun AddScreen(vm: AddViewModel = viewModel(factory = AddViewModel.Factory),onClickToHome: () -> Unit){
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
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
                onNameChange = { vm.firstName = it },
                stringResource(R.string.first_name_error_message),
                vm.firstNameIsValid()
            )

            CustomTextField(
                stringResource(R.string.surname_hint),
                text = vm.surname,
                onNameChange = { vm.surname = it },
                stringResource(R.string.surname_error_message),
                vm.surnameIsValid()
            )

            CustomTextField(
                stringResource(R.string.tel_no_hint),
                text = vm.telNo,
                onNameChange = { vm.telNo = it },
                stringResource(R.string.tel_no_error_message),
                vm.telNoIsValid()
            )

            CustomButton(stringResource(R.string.add),
                clickButton = { vm.addContact()
                    keyboardController?.hide()
                    onClickToHome()})
        }
    }
}
