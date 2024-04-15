package com.example.androidad.presentation.screens.view_delete

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidad.core.ContactApplication
import com.example.androidad.data.Contact
import com.example.androidad.data.InMemoryRepository

class HomeViewModel(private val repo: InMemoryRepository) : ViewModel() {
    val items = mutableStateListOf<Contact>()//No live data in this example

    var selectedContactIndex: Int =-1
    init{
        Log.v("OK","init home")
        items.addAll(repo.getAllContacts())
    }

    fun deleteContact(){  //use this to delete the favourite from the favourites table.
        repo.deleteContact(repo.getAllContacts()[selectedContactIndex])
        items.removeAt(selectedContactIndex)
        selectedContactIndex=-1
        Log.v("OK","repo size " + repo.getAllContacts().size.toString())
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                HomeViewModel(
                    repo = ContactApplication.contactInMemoryRepository
                )
            }
        }

    }
}