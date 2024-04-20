package com.example.androidad.presentation.screens.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidad.core.ContactApplication
import com.example.androidad.data.auth.AuthRepo
import com.example.androidad.data.contact.Contact
import com.example.androidad.data.contact.ContactRepo

class AddViewModel (private val authRepo: AuthRepo,
                    private val contactRepo: ContactRepo
) : ViewModel() {
    var firstName by mutableStateOf(String())
    var surname by mutableStateOf(String())
    var telNo by mutableStateOf(String())

    fun firstNameIsValid():Boolean{
        return firstName.isNotBlank()
    }

    fun surnameIsValid():Boolean{
        return surname.isNotBlank()
    }

    fun telNoIsValid():Boolean{
        return telNo.isNotBlank()
    }

    fun addContact(){
        if(firstNameIsValid() && surnameIsValid() && telNoIsValid()) {
            var newContact = Contact(
                firstName,
                surname,
                telNo
            )
            contactRepo.add(newContact, authRepo.currentUser!!.uid)
            clear()
        }
    }

    private fun clear(){
        firstName =String()
        surname=String()
        telNo=String()
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AddViewModel(
                    authRepo = ContactApplication.container.authRepository,
                    contactRepo = ContactApplication.container.contactRepository
                )
            }
        }

    }
}