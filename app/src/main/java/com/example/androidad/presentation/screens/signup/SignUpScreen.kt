package com.example.androidad.presentation.screens.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidad.R
import com.example.androidad.presentation.components.CustomButton
import com.example.androidad.presentation.components.CustomTextField
import com.example.androidad.presentation.components.SmallSpacer
import com.example.androidad.presentation.screens.signup.components.SignUp
import com.example.androidad.presentation.utils.Util.Companion.showMessage

@Composable
fun SignUpScreen(
    vm: SignUpViewModel = viewModel(factory = SignUpViewModel.Factory),
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current
    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 15.dp),
                    text = "Enter details to sign up",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                CustomTextField(
                    stringResource(R.string.email),
                    text = vm.email,
                    isPasswordField = false,
                    onValueChange = { vm.email = it },
                    stringResource(R.string.email_error_message),
                    errorPresent = !vm.emailIsValid(),
                    showError = vm.submissionFailed
                )
                SmallSpacer(8)
                CustomTextField(
                    stringResource(R.string.password),
                    text = vm.password,
                    isPasswordField = true,
                    onValueChange = { vm.password = it },
                    stringResource(R.string.password_error_message),
                    errorPresent = !vm.passwordIsValid(),
                    showError = vm.submissionFailed
                )
                SmallSpacer(8)
                CustomTextField(
                    stringResource(R.string.first_name_hint),
                    text = vm.firstName,
                    isPasswordField = false,
                    onValueChange = { vm.firstName = it },
                    stringResource(R.string.first_name_error_message),
                    errorPresent = !vm.firstNameIsValid(),
                    showError = vm.submissionFailed
                )
                SmallSpacer(8)
                CustomTextField(
                    stringResource(R.string.last_name_hint),
                    text = vm.lastName,
                    isPasswordField = false,
                    onValueChange = { vm.lastName = it },
                    stringResource(R.string.last_name_error_message),
                    errorPresent = !vm.lastNameIsValid(),
                    showError = vm.submissionFailed

                )
                SmallSpacer(8)
                CustomButton(
                    stringResource(R.string.submit_button),
                    clickButton = {
                        keyboard?.hide()
                        vm.signUpWithEmailAndPassword()
                    }
                )
                Row {
                    CustomButton(
                        stringResource(R.string.back_button),
                        clickButton = {
                            navigateBack()
                        }
                    )
                }
            }
        }
    )

    SignUp(
        vm = vm,
        sendEmailVerification = {
            vm.sendEmailVerification()
        },
        showVerifyEmailMessage = {
            showMessage(context, "Confirm details via email")
        },
        showFailureToSignUpMessage = {
            showMessage(context, "Unable to create sign up due to permissions")
        }
    )
}
