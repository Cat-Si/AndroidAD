package com.example.androidad.presentation.screens.add

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
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


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddScreen(
    vm: AddViewModel = viewModel(factory = AddViewModel.Factory),
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onClickToHome: () -> Unit,
    datePickerState: DatePickerState
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
                    stringResource(R.string.location),
                    text = vm.location,
                    onValueChange = { vm.location = it },
                    errorMessage = stringResource(R.string.location_error),
                    errorPresent = vm.locationIsValid()
                )

                CustomTextField(
                    stringResource(R.string.date),
                    text = vm.date,
                    onValueChange = { vm.date = it },
                    errorMessage = stringResource(R.string.date_error),
                    errorPresent = vm.dateIsValid()
                )

                CustomTextField(
                    stringResource(R.string.date),
                    text = vm.date,
                    onValueChange = { vm.date = it },
                    errorMessage = stringResource(R.string.date_error),
                    errorPresent = vm.dateIsValid()
                )
                CustomButton(
                    stringResource(R.string.date),
                    clickButton = {
                        keyboardController?.hide()
                        datePickerState.displayMode
                    })


                CustomTextField(
                    stringResource(R.string.time),
                    text = vm.time,
                    onValueChange = { vm.time = it },
                    errorMessage = stringResource(R.string.time_error),
                    errorPresent = vm.timeIsValid()
                )
                CustomTextField(
                    stringResource(R.string.injured_party),
                    text = vm.injuredParty,
                    onValueChange = { vm.injuredParty = it },
                    errorMessage = stringResource(R.string.injured_party_error),
                    errorPresent = vm.injuredPartyIsValid()
                )
                CustomTextField(
                    stringResource(R.string.injury),
                    text = vm.injury,
                    onValueChange = { vm.injury = it },
                    errorMessage = stringResource(R.string.injury_error),
                    errorPresent = vm.injuryIsValid(),
                    singleLine = false
                )
                CustomTextField(
                    stringResource(R.string.treatment),
                    text = vm.treatment,
                    onValueChange = { vm.treatment = it },
                    errorMessage = stringResource(R.string.treatment_error),
                    errorPresent = vm.treatmentIsValid(),
                    singleLine = false
                )
                CustomTextField(
                    stringResource(R.string.advice),
                    text = vm.advice,
                    onValueChange = { vm.advice = it },
                    errorMessage = stringResource(R.string.advice_error),
                    errorPresent = vm.adviceIsValid(),
                    singleLine = false
                )
                CustomButton(
                    stringResource(R.string.add),
                    clickButton = {
                        vm.addReport()
                        keyboardController?.hide()
                        onClickToHome()
                    })

            }
        }
    }
}
