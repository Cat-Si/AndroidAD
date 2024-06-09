package com.example.androidad.presentation.screens.login.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.androidad.core.ContactApplication
import com.example.androidad.data.Response
import com.example.androidad.data.user.User
import com.example.androidad.presentation.components.ProgressBar
import com.example.androidad.presentation.screens.login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LogIn(
    vm: LoginViewModel,
    showErrorMessage: (errorMessage: String?) -> Unit,
    navigateToHome: (User) -> Unit,
    navigateToViewReports: (User) -> Unit,
) {


    when (val signInResponse = vm.signInResponse) {
        is Response.Startup -> Unit
        is Response.Loading -> ProgressBar()
        is Response.Success -> {
//            if (ContactApplication.container.isRunningTest || vm.isEmailVerified) {
            val userUID = FirebaseAuth.getInstance().currentUser?.uid ?: ""
            if (userUID.isNotEmpty()) {
                LaunchedEffect(signInResponse) {
                    vm.userRepo.getUser(userUID) { user ->
                        if (user != null) {
                            if (user.admin == true) {
                                navigateToHome(user)
                            } else {
                                navigateToViewReports(user)
                            }
                        }
                    }
                }
            }
//            } else {
//                showErrorMessage("Email not authorised")
//            }
        }

        is Response.Failure -> signInResponse.apply {
            LaunchedEffect(signInResponse.e) {
                showErrorMessage(signInResponse.e.message)
            }
        }
    }
}

