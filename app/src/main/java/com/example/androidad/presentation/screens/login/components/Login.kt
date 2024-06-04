package com.example.androidad.presentation.screens.login.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.androidad.data.Response
import com.example.androidad.presentation.components.ProgressBar
import com.example.androidad.presentation.screens.login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LogIn(
    vm: LoginViewModel,
    showErrorMessage: (errorMessage: String?) -> Unit,
    onClickToHome: () -> Unit,
    onClickToViewReports: () -> Unit,
) {


    when (val signInResponse = vm.signInResponse) {
        is Response.Startup -> Unit //Do nothing
        is Response.Loading -> ProgressBar()
        is Response.Success -> {
            val userUID = FirebaseAuth.getInstance().currentUser?.uid ?: ""
            if (userUID.isNotEmpty()) {
                LaunchedEffect(signInResponse) { // Ensure this runs only once per success response
                    vm.userRepo.getUser(userUID) { user ->
                        if (user?.admin == true) {
                            onClickToHome()
                        } else {
                            onClickToViewReports()
                        }
                    }
                }
            }
        }

        is Response.Failure -> {
            LaunchedEffect(signInResponse.e) {
                showErrorMessage(signInResponse.e.message)
            }
        }

    }
}

