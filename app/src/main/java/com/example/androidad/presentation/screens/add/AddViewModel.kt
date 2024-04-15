package com.example.bottomnav1.presentation.screens.add

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidad.data.InMemoryRepository
import com.example.bottomnav1.core.ContactApplication
import com.example.bottomnav1.data.Contact
import com.example.bottomnav1.data.InMemoryRepository
import java.util.UUID

class AddViewModel (private val repo: InMemoryRepository) : ViewModel() {
    var firstName by mutableStateOf("")
    var surname by mutableStateOf("")
    var telNo by mutableStateOf("")

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
                UUID.randomUUID(),
                firstName,
                surname,
                telNo
            )
            repo.addContact(newContact)
            Log.v("OK", repo.getAllContacts().size.toString())
            clear()
        }
    }

    private fun clear(){
        firstName = String()
        surname=String()
        telNo=String()
    }


    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AddViewModel(
                    repo = ContactApplication.contactInMemoryRepository
                )
            }
        }

    }
}