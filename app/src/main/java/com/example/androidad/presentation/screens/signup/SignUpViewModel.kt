package com.example.androidad.presentation.screens.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidad.core.ContactApplication
import com.example.androidad.data.Response
import com.example.androidad.data.auth.AuthRepo
import com.example.androidad.data.user.User
import com.example.androidad.data.user.UserRepo
import com.google.android.gms.common.api.BooleanResult
import kotlinx.coroutines.launch
import kotlin.random.Random

class SignUpViewModel(
    private val repo: AuthRepo,
    private val userRepo: UserRepo,
) : ViewModel() {
    var email by mutableStateOf(String())
    var password by mutableStateOf(String())
    var firstName by mutableStateOf(String())
    var lastName by mutableStateOf(String())
    var admin: Boolean by mutableStateOf(false)

    var submissionFailed by mutableStateOf(false)

    fun emailIsValid(): Boolean {
        return email.isNotBlank()
    }

    fun passwordIsValid(): Boolean {
        return password.isNotBlank()
    }

    fun firstNameIsValid(): Boolean {
        return firstName.isNotBlank()
    }

    fun lastNameIsValid(): Boolean {
        return lastName.isNotBlank()
    }


    var signUpResponse by mutableStateOf<Response<Boolean>>(Response.Success(false))
        private set

    private var sendEmailVerificationResponse by mutableStateOf<Response<Boolean>>(
        Response.Success(
            false
        )
    )

    fun signUpWithEmailAndPassword() = viewModelScope.launch {
        signUpResponse = Response.Loading
        signUpResponse = repo.firebaseSignUpWithEmailAndPassword(email, password)
    }

    fun sendEmailVerification() = viewModelScope.launch {
        sendEmailVerificationResponse = Response.Loading
        sendEmailVerificationResponse = repo.sendEmailVerification()
        //save user record - trusts user will sign up
        if (firstNameIsValid() && lastNameIsValid()) {
            val randomNumber = Random.nextInt(1000, 9999)
            val user =
                User(
                    repo.currentUser?.email,
                    firstName,
                    lastName,
                    "$firstName $lastName",
                    (lastName + firstName[0]).lowercase() + randomNumber,
                    admin

                )
            userRepo.add(user, repo.currentUser!!.uid)
            submissionFailed = false
        } else {
            submissionFailed = true
        }

    }

//        addContact(user.displayName, user.userName)
//    private fun addContact(displayName: String?, username: String?) {
//
//        var newContact = Contact(
//            firstName,
//            lastName,
//            displayName,
//            username,
//            report = emptyList()
//        )
//        contactRepo.add(newContact, repo.currentUser!!.uid)
//        submissionFailed = false
//
//    }


    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                SignUpViewModel(
                    repo = ContactApplication.container.authRepository,
                    userRepo = ContactApplication.container.userRepository,
                )
            }
        }
    }
}