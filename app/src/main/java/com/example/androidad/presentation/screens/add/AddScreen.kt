package com.example.androidad.presentation.screens.add

import android.annotation.SuppressLint
import android.text.format.DateUtils
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidad.R
import com.example.androidad.presentation.components.BottomNavBar
import com.example.androidad.presentation.components.CustomButton
import com.example.androidad.presentation.components.CustomTextField
import com.example.androidad.presentation.components.DatePickerWithDialog


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddScreen(
    vm: AddViewModel = viewModel(factory = AddViewModel.Factory),
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onClickToHome: () -> Unit,
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
                text = stringResource(R.string.add_form),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            Column {

                CustomTextField(
                    stringResource(R.string.firstAider),
                    text = vm.firstAider,
                    onValueChange = { vm.firstAider = it },
                    errorMessage = stringResource(R.string.firstAider_error),
                    errorPresent = !vm.firstAiderIsValid(),
                    showError = vm.submissionFailed
                )
                CustomTextField(
                    stringResource(R.string.location),
                    text = vm.location,
                    onValueChange = { vm.location = it },
                    errorMessage = stringResource(R.string.location_error),
                    errorPresent = !vm.locationIsValid(),
                    showError = vm.submissionFailed
                )
                DatePickerWithDialog(
                    onDateSelected = { selectedDate ->
                        vm.date = selectedDate
                    }

                )
                CustomTextField(
                    stringResource(R.string.time),
                    text = vm.time,
                    onValueChange = { vm.time = it },
                    errorMessage = stringResource(R.string.time_error),
                    errorPresent = !vm.timeIsValid(),
                    showError = vm.submissionFailed
                )
                CustomTextField(
                    stringResource(R.string.injured_party),
                    text = vm.injuredParty,
                    onValueChange = { vm.injuredParty = it },
                    errorMessage = stringResource(R.string.injured_party_error),
                    errorPresent = !vm.injuredPartyIsValid(),
                    showError = vm.submissionFailed
                )
                CustomTextField(
                    stringResource(R.string.injury),
                    text = vm.injury,
                    onValueChange = { vm.injury = it },
                    errorMessage = stringResource(R.string.injury_error),
                    errorPresent = !vm.injuryIsValid(),
                    singleLine = false,
                    showError = vm.submissionFailed
                )
                CustomTextField(
                    stringResource(R.string.treatment),
                    text = vm.treatment,
                    onValueChange = { vm.treatment = it },
                    errorMessage = stringResource(R.string.treatment_error),
                    errorPresent = !vm.treatmentIsValid(),
                    singleLine = false,
                    showError = vm.submissionFailed
                )
                CustomTextField(
                    stringResource(R.string.advice),
                    text = vm.advice,
                    onValueChange = { vm.advice = it },
                    errorMessage = stringResource(R.string.advice_error),
                    errorPresent = !vm.adviceIsValid(),
                    singleLine = false,
                    showError = vm.submissionFailed
                )
                CustomButton(
                    stringResource(R.string.add),
                    clickButton = {
                        vm.addReport()
                        keyboardController?.hide()
                        if(!vm.submissionFailed) {onClickToHome() }
                    })

            }
        }
    }
}
