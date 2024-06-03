package com.example.androidad.presentation.screens.signup

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidad.core.ContactApplication
import com.example.androidad.data.Response
import com.example.androidad.data.auth.AuthRepo
import com.example.androidad.data.contact.Contact
import com.example.androidad.data.contact.ContactRepo
import com.example.androidad.data.user.User
import com.example.androidad.data.user.UserRepo
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.launch
import kotlin.random.Random

class SignUpViewModel(
    private val repo: AuthRepo,
    private val userRepo: UserRepo, private val contactRepo: ContactRepo
) : ViewModel() {
    var email by mutableStateOf(String())
    var password by mutableStateOf(String())
    var firstName by mutableStateOf(String())
    var lastName by mutableStateOf(String())
    var userName by mutableStateOf(String())
    var displayName by mutableStateOf(String())

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

    fun validUserName(firstName: String, lastName: String): String {
        if (firstNameIsValid() && lastNameIsValid()) {
            val randomNumber = Random.nextInt(1000, 9999)
            userName = (lastName + firstName[0]).lowercase() + randomNumber
        }
        return userName
    }

    fun validDisplayName(firstName: String, lastName: String): String {
        if (firstNameIsValid() && lastNameIsValid()) {

            displayName = "$firstName $lastName"
        }
        return displayName
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
        val user =
            User(
                repo.currentUser?.email,
                displayName = validDisplayName(firstName, lastName),
                userName = validUserName(firstName, lastName)
            )
        userRepo.add(user, repo.currentUser!!.uid)
        addContact(user.displayName, user.userName)

    }

    private fun addContact(displayName: String?, username: String?) {

        var newContact = Contact(
            firstName,
            lastName,
            displayName,
            username,
            report = emptyList()
        )
        contactRepo.add(newContact, repo.currentUser!!.uid)
        submissionFailed = false

    }


    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                SignUpViewModel(
                    repo = ContactApplication.container.authRepository,
                    userRepo = ContactApplication.container.userRepository,
                    contactRepo = ContactApplication.container.contactRepository
                )
            }
        }
    }
}