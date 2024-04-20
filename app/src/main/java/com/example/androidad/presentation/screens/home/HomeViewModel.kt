package com.example.androidad.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidad.core.ContactApplication
import com.example.androidad.data.DatabaseResult
import com.example.androidad.data.DatabaseState
import com.example.androidad.data.auth.AuthRepo
import com.example.androidad.data.contact.Contact
import com.example.androidad.data.contact.ContactRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val authRepo: AuthRepo, private val repo: ContactRepo) : ViewModel() {
    private val _userState = MutableStateFlow(DatabaseState<Contact>())
    val userState: StateFlow<DatabaseState<Contact>> = _userState.asStateFlow()//Monitored by component for recomposition on change

    var selectedContact: Contact?= null

    init {
        getListOfContacts(authRepo.currentUser!!.uid)
    }

    fun contactHasBeenSelected(): Boolean = selectedContact!=null

    private fun getListOfContacts(userId: String) = viewModelScope.launch {
        repo.getAll(userId).collect { result ->
            when(result) {
                is DatabaseResult.Success -> {
                    _userState.update { it.copy(data = result.data) }
                }
                is DatabaseResult.Error -> {
                    _userState.update {
                        it.copy(errorMessage = result.exception.message!!)
                    }
                }
                is DatabaseResult.Loading -> {
                    _userState.update { it.copy(isLoading = true) }
                }
            }
        }
    }
    fun deleteContact(){
        if (contactHasBeenSelected()) {
            repo.delete(selectedContact!!)
            selectedContact = null
        }
    }

        // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                HomeViewModel(
                    authRepo = ContactApplication.container.authRepository,
                    repo = ContactApplication.container.contactRepository
                )
            }
        }

    }
}
