package com.example.navigation1.presentation.screens.edit

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidad.core.ContactApplication
import com.example.androidad.data.Contact
import com.example.androidad.data.InMemoryRepository

class EditViewModel (private val repo: InMemoryRepository) : ViewModel() {
    private var selectedContact : Contact? = null

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

    fun getContacts(selectedIndex: Int){//Display when screen loads
        if(selectedContact==null) {
            selectedContact = repo.getAllContacts()[selectedIndex]
            Log.v("OK",selectedContact!!.toString())
            firstName = selectedContact!!.firstName
            surname = selectedContact!!.surname
            telNo = selectedContact!!.telNo
        }
    }

    fun updateContact(){
        selectedContact!!.firstName = firstName
        selectedContact!!.surname = surname
        selectedContact!!.telNo = telNo
        repo.edit(selectedContact!!)
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                EditViewModel(
                    repo = ContactApplication.contactInMemoryRepository)
            }
        }
    }
}