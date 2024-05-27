package com.example.androidad.presentation.screens.edit

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidad.R
import com.example.androidad.data.report.Report
import com.example.androidad.presentation.components.BottomNavBar
import com.example.androidad.presentation.components.CustomButton
import com.example.androidad.presentation.components.CustomTextField
import com.example.androidad.presentation.components.CustomToolTip
import com.example.androidad.presentation.components.DatePickerWithDialog

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditScreen(
    vm: EditViewModel = viewModel(factory = EditViewModel.Factory),
    modifier: Modifier = Modifier,
    navController: NavHostController,
    selectedReport: Report,
    onClickToHome: () -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        // Called on launch
        vm.setSelectedReport(selectedReport)
    }


    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 15.dp),
                text = stringResource(R.string.edit),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .weight(1f)
            ) {
                item {
                    CustomToolTip(
                        {
                            CustomTextField(
                                stringResource(R.string.firstAider),
                                text = vm.firstAider,
                                onValueChange = { vm.firstAider = it },
                                errorMessage = stringResource(R.string.firstAider_error),
                                errorPresent = !vm.firstAiderIsValid(),
                                showError = vm.submissionFailed
                            )
                        },
                        text = stringResource(R.string.firstAider_tooltip)
                    )
                }
                item {
                    CustomToolTip(
                        {
                            CustomTextField(
                                stringResource(R.string.location),
                                text = vm.location,
                                onValueChange = { vm.location = it },
                                errorMessage = stringResource(R.string.location_error),
                                errorPresent = !vm.locationIsValid(),
                                showError = vm.submissionFailed
                            )
                        }, text = stringResource(R.string.location_tooltip)
                    )
                }
                item {
                    CustomToolTip(
                        {
                            DatePickerWithDialog(
                                onDateSelected = { selectedDate ->
                                    vm.date = selectedDate
                                },
                                text = vm.date,
                                onValueChange = { vm.date = it },
                                label = { Text(stringResource(R.string.date)) },
                                errorMessage = stringResource(R.string.date_error),
                                errorPresent = !vm.dateIsValid(),
                                showError = vm.submissionFailed
                            )
                        }, text = stringResource(R.string.date_tooltip)
                    )
                }
                item {
                    CustomToolTip(
                        {
                            CustomTextField(
                                stringResource(R.string.time),
                                text = vm.time,
                                onValueChange = { vm.time = it },
                                errorMessage = stringResource(R.string.time_error),
                                errorPresent = !vm.timeIsValid(),
                                showError = vm.submissionFailed
                            )
                        },
                        text = stringResource(R.string.time_tooltip)
                    )
                }
                item {
                    CustomToolTip(
                        {
                            CustomTextField(
                                stringResource(R.string.injured_party),
                                text = vm.injuredParty,
                                onValueChange = { vm.injuredParty = it },
                                errorMessage = stringResource(R.string.injured_party_error),
                                errorPresent = !vm.injuredPartyIsValid(),
                                showError = vm.submissionFailed
                            )
                        }, text = stringResource(R.string.injured_party_tooltip)
                    )
                }
                item {
                    CustomToolTip(
                        {
                            CustomTextField(
                                stringResource(R.string.injury),
                                text = vm.injury,
                                onValueChange = { vm.injury = it },
                                errorMessage = stringResource(R.string.injury_error),
                                errorPresent = !vm.injuryIsValid(),
                                singleLine = false,
                                showError = vm.submissionFailed
                            )
                        },
                        text = stringResource(R.string.injury_tooltip)
                    )
                }
                item {
                    CustomToolTip(
                        {
                            CustomTextField(
                                stringResource(R.string.treatment),
                                text = vm.treatment,
                                onValueChange = { vm.treatment = it },
                                errorMessage = stringResource(R.string.treatment_error),
                                errorPresent = !vm.treatmentIsValid(),
                                singleLine = false,
                                showError = vm.submissionFailed
                            )
                        },
                        text = stringResource(R.string.treatment_tooltip)
                    )
                }
                item {
                    CustomToolTip(
                        {
                            CustomTextField(
                                stringResource(R.string.advice),
                                text = vm.advice,
                                onValueChange = { vm.advice = it },
                                errorMessage = stringResource(R.string.advice_error),
                                errorPresent = !vm.adviceIsValid(),
                                singleLine = false,
                                showError = vm.submissionFailed
                            )
                        },
                        text = stringResource(R.string.advice_tooltip)
                    )
                }
            }

            Row(
                modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement.Center
            ) {

                CustomButton(
                    stringResource(R.string.edit),
                    clickButton = {
                        vm.updateReport()
                        onClickToHome()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
                CustomButton(stringResource(R.string.delete),
                    clickButton = {
                        vm.deleteReport()
                        onClickToHome()
                    })
            }
        }
    }
}
